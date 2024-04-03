package shop.mtcoding.projectjobplan.subscribe;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.SessionUser;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final UserJpaRepository userJpaRepository;
    private final SubscribeJpaRepository subscribeJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;

    @Transactional // 공고 구독
    public SubscribeResponse.BoardDTO createBoardSubscription(SessionUser sessionUser, int boardId) {
        User user = userJpaRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재하지 않는 유저입니다."));
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("존재하지 않는 공고입니다."));
        Subscribe subscribe = new Subscribe(user, board);
        Subscribe result = subscribeJpaRepository.save(subscribe);

        return new SubscribeResponse.BoardDTO(result.getBoard());
    }

    @Transactional // 이력서 구독
    public SubscribeResponse.ResumeDTO createResumeSubscription(SessionUser sessionUser, int resumeId) {
        User user = userJpaRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재하지 않는 유저입니다."));
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        Subscribe subscribe = new Subscribe(user, resume);
        Subscribe result = subscribeJpaRepository.save(subscribe);

        return new SubscribeResponse.ResumeDTO(result.getResume());
    }

    @Transactional(readOnly = true) // 구독 목록보기
    public SubscribeResponse.DTO getSubscription(int userId, Pageable pageable) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("존재하지 않는 유저입니다."));
        List<Subscribe> subscription = subscribeJpaRepository.findByUserId(userId).get();
        // DTO가 완성되는 시점까지 DB 연결 유지
        return new SubscribeResponse.DTO(user, subscription, pageable);
    }

    @Transactional // 공고 구독 취소
    public void removeBoardSubscription(int boardId, int userId) {
        Subscribe subscribe = subscribeJpaRepository.findByBoardIdAndUserId(boardId, userId)
                .orElseThrow(() -> new Exception404("구독되어 있지 않습니다."));

        subscribeJpaRepository.delete(subscribe);
    }

    @Transactional // 이력서 구독 취소
    public void removeResumeSubscription(int resumeId, int userId) {
        Subscribe subscribe = subscribeJpaRepository.findByResumeIdAndUserId(resumeId, userId)
                        .orElseThrow(() -> new Exception404("구독되어 있지 않습니다."));

        subscribeJpaRepository.delete(subscribe);
    }
}
