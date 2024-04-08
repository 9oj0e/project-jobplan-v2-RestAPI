package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.*;

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
        User user = userJpaRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        String encodedData = requestDTO.getEncodedImg();
        byte[] decodedByte = Base64.getDecoder().decode(encodedData);
        String newFilename = UUID.randomUUID() + "_" + requestDTO.getFileName() + ".jpg";
        Path newFilePath = Paths.get("./upload/" + newFilename);
        Files.write(newFilePath, decodedByte);
        user.picUpdate(newFilename);

        return new UserResponse.PicDTO(requestDTO.getFileName(), newFilePath.toString());
    }
}
