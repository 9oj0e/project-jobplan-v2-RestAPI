package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception400;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception401;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan._core.utils.JwtUtil;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.apply.ApplyJpaRepository;
import shop.mtcoding.projectjobplan.offer.Offer;
import shop.mtcoding.projectjobplan.offer.OfferJpaRepository;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.skill.SkillJpaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;
    private final OfferJpaRepository offerJpaRepository;
    private final SkillJpaRepository skillJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;

    @Transactional // 회원가입
    public User createUser(UserRequest.JoinDTO requestDTO) {
        Optional<User> userOP = userJpaRepository.findByUsername(requestDTO.getUsername());
        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저입니다.");
        }
        User user = new User(requestDTO);

        return userJpaRepository.save(user);
    }

    // 로그인
    public String getUser(UserRequest.LoginDTO requestDTO) {
        User user = userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));
        String jwt = JwtUtil.create(user);

        return jwt;
    }

    @Transactional(readOnly = true) // 회원정보 보기
    public UserResponse.ProfileDTO getUser(Integer sessionUserId, Integer boardId, Integer resumeId, Pageable pageable) {
        User user = userJpaRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        List<Apply> applyList;
        List<Offer> offerList;
        if (user.getIsEmployer()) { // 기업 마이페이지
            if (boardId == null) { // 모든 지원자 현황 보기 & 모든 제안 현황 보기
                applyList = applyJpaRepository.findByBoardUserId(user.getId());
                offerList = offerJpaRepository.findByBoardUserId(user.getId());
            } else { // 공고별 지원자 보기 & 공고별 제안 현황 보기
                applyList = applyJpaRepository.findByBoardId(boardId);
                offerList = offerJpaRepository.findByBoardId(boardId);
            }
        } else { // 개인 마이페이지
            if (resumeId == null) { // 모든 지원 현황 보기 & 모든 제안 현황 보기
                applyList = applyJpaRepository.findByResumeUserId(user.getId());
                offerList = offerJpaRepository.findByResumeUserId(user.getId());
            } else { // 이력서별 지원 현황 보기 & 이력서별 제안 현황 보기
                applyList = applyJpaRepository.findByResumeId(resumeId);
                offerList = offerJpaRepository.findByResumeId(resumeId);
            }
        }
        // 평점 보기
        Double rating = ratingJpaRepository.findRatingAvgByUserId(user.getId()).orElse(0.0);
        // DTO가 완성되는 시점까지 DB 연결 유지
        return new UserResponse.ProfileDTO(user, applyList, offerList, rating, pageable);
    }

    // 회원 수정 폼
    public UserResponse.UpdateFormDTO getUser(int userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        return new UserResponse.UpdateFormDTO(user);
    }

    @Transactional // 회원 수정
    public SessionUser setUser(int userId, UserRequest.UpdateDTO requestDTO) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));
        user.update(requestDTO);

        return new SessionUser(user);
    }

    @Transactional // 회원 삭제
    public void removeUser(int userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        userJpaRepository.delete(user);
    }

    @Transactional // 스킬 추가, 수정 및 삭제
    public List<UserResponse.SkillDTO> createSkillList(UserRequest.SkillDTO requestDTO, int userId) {
        User user = userJpaRepository.findById(userId).get();
        List<Skill> skills = new ArrayList<>();
        for (String skillName : requestDTO.getSkill()) {
            Skill skill = Skill.builder()
                    .user(user)
                    .name(skillName)
                    .build();
            skills.add(skill);
        }
        // dto.getSkill().stream().forEach(s -> new Skill(user, s));
        List<Skill> skillFound = skillJpaRepository.findByUserId(userId).orElse(null);
        if (skillFound != null) {
            skillJpaRepository.deleteAll(skillFound);
        }
        skillJpaRepository.saveAll(skills);
        List<UserResponse.SkillDTO> skillList = skills.stream().map(skill -> new UserResponse.SkillDTO(skill)).toList();

        return skillList;
    }

    @Transactional
    public UserResponse.PicDTO picUpload(UserRequest.PicDTO requestDTO, Integer sessionUserId) throws IOException {
        // byte[] img = Base64.getDecoder().decode(); // base64 확장자, 파싱하는 법 gpt...
        User user = userJpaRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        // 기본 파일 이름 설정
        String defaultImgFilename = user.getIsEmployer() ? "default/business.png" : "default/avatar.png";
        // 기존의 파일 이름, 경로 확보
        String currentImgFilename = user.getImgFilename();
        Path currentFilePath = Paths.get("./upload/" + user.getImgFilename());
        // 삭제 요청 여부 및 동일 파일 여부 확인
        boolean isEmpty = requestDTO.getImgFile() == null || requestDTO.getImgFile().isEmpty();
        boolean isSameFile = currentImgFilename.equals(requestDTO.getImgFile().getOriginalFilename());
        if (!isEmpty) { // 이미지 업로드 요청
            if (!isSameFile) { // 동일 파일 여부 확인
                MultipartFile imgFile = requestDTO.getImgFile();
                String newImgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename(); // 파일 이름
                Path newFilePath = Paths.get("./upload/" + newImgFilename); // 파일 저장 경로
                Files.delete(currentFilePath);
                Files.write(newFilePath, imgFile.getBytes());
                // 새로운 이미지 파일 경로를 업데이트
                user.picPost(newImgFilename);
            }
        } else { // 이미지 삭제 요청
            Files.delete(currentFilePath);
            user.picPost(defaultImgFilename); // 기본 파일로 재설정 (실질적 삭제)
        }
        return new UserResponse.PicDTO("x");
    }
}
