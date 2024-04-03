package shop.mtcoding.projectjobplan.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.SessionUser;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final UserJpaRepository userJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;

    @Transactional // 공고 주인 평가
    public RatingResponse.DTO createRating(SessionUser sessionUser, RatingRequest.RateBoardUser requestDTO) {
        User user = userJpaRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재하지 않는 유저입니다."));
        Board board = boardJpaRepository.findById(requestDTO.getBoardId()).get();
        Rating rating = new Rating(user, board, requestDTO.getRating());
        Rating result = ratingJpaRepository.save(rating);

        return new RatingResponse.DTO(result);
    }

    @Transactional // 이력서 주인 평가
    public RatingResponse.DTO createRating(SessionUser sessionUser, RatingRequest.RateResumeUser requestDTO) {
        User user = userJpaRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("존재하지 않는 유저입니다."));
        Resume resume = resumeJpaRepository.findById(requestDTO.getResumeId()).get();
        Rating rating = new Rating(user, resume, requestDTO.getRating());
        Rating result = ratingJpaRepository.save(rating);

        return new RatingResponse.DTO(result);
    }
}