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
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final SubscribeJpaRepository subscribeJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;

    @Transactional // 공고 구독
    public SubscribeResponse.BoardDTO createBoardSubscription(User sessionUser, int boardId) {
        Board board = boardJpaRepository.findById(boardId).get();
        Subscribe subscribe = new Subscribe(sessionUser, board);
        Subscribe result = subscribeJpaRepository.save(subscribe);

        return new SubscribeResponse.BoardDTO(result.getBoard());
    }

    @Transactional // 이력서 구독
    public SubscribeResponse.ResumeDTO createResumeSubscription(User sessionUser, int resumeId) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        Subscribe subscribe = new Subscribe(sessionUser, resume);
        Subscribe result = subscribeJpaRepository.save(subscribe);

        return new SubscribeResponse.ResumeDTO(result.getResume());
    }

    @Transactional(readOnly = true)
    public SubscribeResponse.DTO getSubscription(int userId, Pageable pageable) { // 구독 리스트 불러오기
        User user = userJpaRepository.findById(userId).get();
        List<Subscribe> subscription = subscribeJpaRepository.findByUserId(userId).get();

        return new SubscribeResponse.DTO(user, subscription, pageable);
    }

    @Transactional // 공고 구독 중지
    public void removeBoardSubscription(int boardId, int userId) {
        Subscribe subscribe = subscribeJpaRepository.findByBoardIdAndUserId(boardId, userId)
                .orElseThrow(() -> new Exception404("구독되어 있지 않습니다."));

        subscribeJpaRepository.delete(subscribe);
    }

    @Transactional // 이력서 구독 중지
    public void removeResumeSubscription(int resumeId, int userId) {
        Subscribe subscribe = subscribeJpaRepository.findByResumeIdAndUserId(resumeId, userId)
                        .orElseThrow(() -> new Exception404("구독되어 있지 않습니다."));

        subscribeJpaRepository.delete(subscribe);
    }
}
