package shop.mtcoding.projectjobplan.rating;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@RestController
public class RatingController {
    private final HttpSession session;
    private final RatingService ratingService;

    @PostMapping("api/boards/{boardId}")
    public ResponseEntity<?> rateBoard(@PathVariable int boardId, @RequestBody RatingRequest.RateBoardUser requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        RatingResponse.DTO respDTO  = ratingService.createRating(sessionUser, requestDTO);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("api/resumes/{resumeId}")
    public ResponseEntity<?> rateResume(@PathVariable int resumeId, @RequestBody RatingRequest.RateResumeUser requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        RatingResponse.DTO respDTO = ratingService.createRating(sessionUser, requestDTO);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
