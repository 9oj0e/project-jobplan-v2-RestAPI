package shop.mtcoding.projectjobplan.rating;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class RatingController {
    private final HttpSession session;
    private final RatingService ratingService;

    @PostMapping("/boards/{boardId}/rate")
    public String rateBoard(@PathVariable int boardId, RatingRequest.RateBoardUser requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ratingService.createRating(sessionUser, requestDTO);

        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/resumes/{resumeId}/rate")
    public String rateResume(@PathVariable int resumeId, RatingRequest.RateResumeUser requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ratingService.createRating(sessionUser, requestDTO);

        return "redirect:/resumes/" + resumeId;
    }
}
