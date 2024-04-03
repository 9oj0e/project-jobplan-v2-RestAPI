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
    private final RatingJpaRepository ratingJpaRepository;
    private final SubscribeJpaRepository subscribeJpaRepository;
    private final SkillJpaRepository skillJpaRepository;

    @Transactional
    public BoardResponse.SaveDTO createBoard(BoardRequest.SaveDTO requestDTO, SessionUser sessionUser) {
        User user = userJpaRepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception404("조회된 데이터가 없습니다."));
        Board board = boardJpaRepository.save(requestDTO.toEntity(user));
        List<Skill> skillList = new ArrayList<>();
        for (String skillName : requestDTO.getSkill()) {
            Skill skill = Skill.builder()
                    .board(board)
                    .name(skillName)
                    .build();
            skillList.add(skill);
        }
        skillJpaRepository.saveAll(skillList);

        return new BoardResponse.SaveDTO(board);
    }

    @Transactional(readOnly = true)
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
        return new BoardResponse.DetailDTO(board, rating, isBoardOwner, hasSubscribed, hasRated);
    }

    @Transactional(readOnly = true)
    public BoardResponse.ListingsDTO getAllBoard(Pageable pageable, Integer sessionUserId, String skill, String address, String keyword) {
        List<Board> boards;
        if (skill != null) { // 기술별 검색시
            boards = boardJpaRepository.findAllJoinUserWithSkill(skill).orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        } else if (address != null) { // 지역별 검색시
            boards = boardJpaRepository.findAllJoinUserWithAddress(address).orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        } else if (keyword != null) { // 검색창 이용시
            boards = boardJpaRepository.findAllJoinUserWithKeyword(keyword).orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        } else { // 모든 페이지
            boards = boardJpaRepository.findAllJoinUser().orElseThrow(() -> new Exception404("조회된 게시글이 없습니다."));
        }
        List<Object[]> recommendations = new ArrayList<>();
        if (sessionUserId != null) {
            List<Skill> skills = skillJpaRepository.findByUserId(sessionUserId)
                    .orElseThrow(() -> new Exception404("가진 스킬이 없습니다."));
            recommendations = boardQueryRepository.findWithSkill(skills);
        }
        return new BoardResponse.ListingsDTO(pageable, boards, recommendations, skill, address, keyword);
    }

    @Transactional
    public List<BoardResponse.IndexDTO> getAllBoardOnIndex() { // index
        final int limit = 8;
        List<Board> boardList = boardJpaRepository.findAllJoinUser(limit).get();
        List<BoardResponse.IndexDTO> responseDTO = new ArrayList<>();
        boardList.stream().forEach(board -> {
            responseDTO.add(new BoardResponse.IndexDTO(board));
        });

        return responseDTO;
    }

    // 공고수정폼
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

    @Transactional // 공고수정
    public BoardResponse.UpdateDTO setBoard(int boardId, BoardRequest.UpdateDTO requestDTO, SessionUser sessionUser) {
        // 조회 및 예외 처리
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));

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
        List<Skill> skillFound = skillJpaRepository.findByBoardId(boardId).orElse(null);
        if (skillFound != null) {
            skillJpaRepository.deleteAll(skillFound);
        }
        // 스킬 수정
        skillJpaRepository.saveAll(skillList);
        // 글 수정
        board.update(requestDTO);

        return new BoardResponse.UpdateDTO(board);
    }

    // 공고삭제
    @Transactional
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
