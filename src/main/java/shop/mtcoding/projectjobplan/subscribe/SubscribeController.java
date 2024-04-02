package shop.mtcoding.projectjobplan.subscribe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class SubscribeController {
    private final HttpSession session;
    private final SubscribeService subscribeService;

    @PostMapping("/boards/{boardId}/subscribe") // 공고 구독
    public String subscribeBoard(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeService.createBoardSubscription(sessionUser, boardId);

        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/resumes/{resumeId}/subscribe") // 이력서 구독
    public String subscribeResume(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeService.createResumeSubscription(sessionUser, resumeId);

        return "redirect:/resumes/" + resumeId;
    }

    @GetMapping("/users/{userId}/subscription") // 구독 리스트
    public String subscription(@PathVariable int userId,
                               HttpServletRequest request,
                               @PageableDefault(size = 3) Pageable pageable) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        SubscribeResponse.DTO subscription = subscribeService.getSubscription(sessionUser.getId(), pageable);
        if (subscription.getPage().isEmpty()) {
            request.setAttribute("subscription", false);
        }
        request.setAttribute("subscription", subscription);

        return "user/subscription";
    }

    @PostMapping("/boards/{boardId}/unsubscribe")
    public String unsubscribeBoard(@PathVariable int boardId, @RequestParam boolean fromSubscription) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeService.removeBoardSubscription(boardId, sessionUser.getId());

        if (fromSubscription) {
            return "redirect:/users/" + sessionUser.getId() + "/subscription";
        } else {
            return "redirect:/boards/" + boardId;
        }
    }

    @PostMapping("/resumes/{resumeId}/unsubscribe")
    public String unsubscribeResume(@PathVariable int resumeId, @RequestParam boolean fromSubscription) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeService.removeResumeSubscription(resumeId, sessionUser.getId());

        if (fromSubscription) {
            return "redirect:/users/" + sessionUser.getId() + "/subscription";
        } else {
            return "redirect:/resumes/" + resumeId;
        }
    }
}
