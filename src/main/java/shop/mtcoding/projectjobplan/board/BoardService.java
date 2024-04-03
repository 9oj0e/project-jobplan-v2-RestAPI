package shop.mtcoding.projectjobplan.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception403;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.rating.Rating;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.skill.SkillJpaRepository;
import shop.mtcoding.projectjobplan.subscribe.Subscribe;
import shop.mtcoding.projectjobplan.subscribe.SubscribeJpaRepository;
import shop.mtcoding.projectjobplan.user.SessionUser;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final UserJpaRepository userJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final SkillJpaRepository skillJpaRepository;
    private final SubscribeJpaRepository subscribeJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;

    @Transactional // 공고 작성하기
    public BoardResponse.SaveDTO createBoard(BoardRequest.SaveDTO requestDTO, SessionUser sessionUser) {
        User user = userJpaRepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception404("조회된 데이터가 없습니다."));
        Board board = boardJpaRepository.save(requestDTO.toEntity(user));
        // 스킬 배열 생성
        List<Skill> skillList = new ArrayList<>();
        for (String skillName : requestDTO.getSkill()) {
            Skill skill = Skill.builder()
                    .board(board)
                    .name(skillName)
                    .build();
            skillList.add(skill);
        }
        // 스킬 추가
        skillJpaRepository.saveAll(skillList);

        return new BoardResponse.SaveDTO(board);
    }

    @Transactional(readOnly = true) // 공고 상세보기
    public BoardResponse.DetailDTO getBoardInDetail(int boardId, Integer sessionUserId) {
        Board board = boardJpaRepository.findById(boardId).orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        Double rating = ratingJpaRepository.findRatingAvgByUserId(board.getUser().getId()).orElse(0.0);

        boolean isBoardOwner = false;
        boolean hasRated = false;
        boolean hasSubscribed = false;
        boolean hasApplied = false;
        if (sessionUserId != null) {
            isBoardOwner = board.getUser().getId() == sessionUserId ? true : false;
            Optional<Subscribe> optionalSubscribe = subscribeJpaRepository.findByBoardIdAndUserId(board.getId(), sessionUserId);
            hasSubscribed = optionalSubscribe.isPresent() ? true : false;
            Optional<Rating> optionalRating = ratingJpaRepository.findByRaterIdAndSubjectId(sessionUserId, board.getUser().getId());
            hasRated = optionalRating.isPresent() ? true : false;
        }
        // DTO가 완성되는 시점까지 DB 연결 유지.
        return new BoardResponse.DetailDTO(board, rating, isBoardOwner, hasSubscribed, hasRated);
    }

    @Transactional(readOnly = true) // 공고 목록보기
    public BoardResponse.ListingsDTO getAllBoard(Pageable pageable, Integer sessionUserId, String skill, String address, String keyword) {
        List<Board> boards; // 공고..
        if (skill != null) { // 스킬 검색
            boards = boardJpaRepository.findAllJoinUserWithSkill(skill).orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        } else if (address != null) { // 지역 검색
            boards = boardJpaRepository.findAllJoinUserWithAddress(address).orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        } else if (keyword != null) { // 키워드 검색
            boards = boardJpaRepository.findAllJoinUserWithKeyword(keyword).orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        } else { // 전체 보기
            boards = boardJpaRepository.findAllJoinUser().orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        }
        List<Object[]> recommendations = new ArrayList<>(); // 추천 공고 (스킬 기반 매칭) (빈 컬랙션 DTO에서 처리)
        if (sessionUserId != null) { // 추천 공고 보기 (sessionUser가 있는 경우에만)
            List<Skill> skills = skillJpaRepository.findByUserId(sessionUserId)
                    .orElseThrow(() -> new Exception404("가진 스킬이 없습니다."));
            recommendations = boardQueryRepository.findWithSkill(skills);
        }
        // DTO가 완성되는 시점까지 DB 연결 유지
        return new BoardResponse.ListingsDTO(pageable, boards, recommendations, skill, address, keyword);
    }

    @Transactional // 메인 페이지
    public List<BoardResponse.IndexDTO> getAllBoardOnIndex() { // index
        final int limit = 8;
        List<Board> boardList = boardJpaRepository.findAllJoinUser(limit).get();
        List<BoardResponse.IndexDTO> responseDTO = new ArrayList<>();
        boardList.stream().forEach(board -> {
            responseDTO.add(new BoardResponse.IndexDTO(board));
        });

        return responseDTO;
    }

    // 공고 수정 폼
    public BoardResponse.UpdateFormDTO getBoard(int boardId, SessionUser sessionUser) {
        // 조회 및 예외 처리
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("해당 공고의 수정페이지로 이동할 권한이 없습니다.");
        }

        return new BoardResponse.UpdateFormDTO(boardJpaRepository.findById(boardId).get());
    }

    @Transactional // 공고 수정
    public BoardResponse.UpdateDTO setBoard(int boardId, BoardRequest.UpdateDTO requestDTO, SessionUser sessionUser) {
        // 조회 및 예외 처리
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));
        // 스킬 배열 생성
        List<Skill> skillList = new ArrayList<>();
        for (String skillName : requestDTO.getSkill()) {
            Skill skill = Skill.builder()
                    .board(board)
                    .name(skillName)
                    .build();
            skillList.add(skill);
        }
        // 권한 처리
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("해당 공고를 수정할 권한이 없습니다.");
        }
        // 해당 공고에 스킬이 있는지 확인 후, 있으면 삭제, 없으면 그대로 추가.
        List<Skill> skills = skillJpaRepository.findByBoardId(boardId).orElse(null);
        if (skills != null) {
            skillJpaRepository.deleteAll(skills);
        }
        skillJpaRepository.saveAll(skillList);
        board.update(requestDTO);

        return new BoardResponse.UpdateDTO(board);
    }

    @Transactional // 공고 삭제
    public void removeBoard(int id, SessionUser sessionUser) {
        // 조회 및 예외 처리
        Board board = boardJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("해당 공고를 삭제할 권한이 없습니다.");
        }

        boardJpaRepository.delete(board);
    }
}
