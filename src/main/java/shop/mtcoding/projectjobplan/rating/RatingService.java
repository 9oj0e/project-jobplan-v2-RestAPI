package shop.mtcoding.projectjobplan.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingJpaRepository ratingJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;

    @Transactional // 공고 주인 평가
    public RatingResponse.DTO createRating(User sessionUser, RatingRequest.RateBoardUser requestDTO) {
        Board board = boardJpaRepository.findById(requestDTO.getBoardId()).get();
        Rating rating = new Rating(sessionUser, board, requestDTO.getRating());
        Rating result = ratingJpaRepository.save(rating);

        return new RatingResponse.DTO(result);
    }

    @Transactional // 이력서 주인 평가
    public RatingResponse.DTO createRating(User sessionUser, RatingRequest.RateResumeUser requestDTO) {
        Resume resume = resumeJpaRepository.findById(requestDTO.getResumeId()).get();
        Rating rating = new Rating(sessionUser, resume, requestDTO.getRating());
        Rating result = ratingJpaRepository.save(rating);

        return new RatingResponse.DTO(result);
    }
}
