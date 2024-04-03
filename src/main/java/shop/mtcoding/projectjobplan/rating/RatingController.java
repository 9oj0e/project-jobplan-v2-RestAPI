package shop.mtcoding.projectjobplan.rating;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.SessionUser;

@RequiredArgsConstructor
@RestController
public class RatingController {
    private final HttpSession session;
    private final RatingService ratingService;

    @PostMapping("/api/boards/{boardId}/rating") // 공고 주인 평가
    public ResponseEntity<?> rateBoard(@PathVariable int boardId, @Valid @RequestBody RatingRequest.RateBoardUser requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        RatingResponse.DTO responseDTO = ratingService.createRating(sessionUser, requestDTO);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }

    @PostMapping("/api/resumes/{resumeId}/rating") // 이력서 주인 평가
    public ResponseEntity<?> rateResume(@PathVariable int resumeId, @RequestBody RatingRequest.RateResumeUser requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        RatingResponse.DTO responseDTO = ratingService.createRating(sessionUser, requestDTO);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }
}
