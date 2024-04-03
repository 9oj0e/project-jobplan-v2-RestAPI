package shop.mtcoding.projectjobplan.subscribe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.SessionUser;

@RequiredArgsConstructor
@RestController
public class SubscribeController {
    private final HttpSession session;
    private final SubscribeService subscribeService;

    @PostMapping("/api/boards/{boardId}/subscription") // 공고 구독
    public ResponseEntity<?> subscribeBoard(@PathVariable int boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SubscribeResponse.BoardDTO responseDTO = subscribeService.createBoardSubscription(sessionUser, boardId);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }

    @PostMapping("api/resumes/{resumeId}/subscription") // 이력서 구독
    public ResponseEntity<?> subscribeResume(@PathVariable int resumeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SubscribeResponse.ResumeDTO responseDTO = subscribeService.createResumeSubscription(sessionUser, resumeId);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }

    @GetMapping("/api/users/{userId}/subscription") // 구독 목록보기
    public ResponseEntity<?> subscription(@PathVariable int userId,
                                          HttpServletRequest request,
                                          @PageableDefault(size = 3) Pageable pageable) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SubscribeResponse.DTO subscription = subscribeService.getSubscription(sessionUser.getId(), pageable);
        if (subscription.getPage().isEmpty()) {
            request.setAttribute("subscription", false);
        }
        request.setAttribute("subscription", subscription);

        return ResponseEntity.ok(new ApiUtil(subscription));
    }

    @DeleteMapping("/api/boards/{boardId}/subscription") // 공고 구독 취소
    public ResponseEntity<?> unsubscribeBoard(@PathVariable int boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        subscribeService.removeBoardSubscription(boardId, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil(null));
    }

    @DeleteMapping("/api/resumes/{resumeId}/subscription") // 이력서 구독 취소
    public ResponseEntity<?> unsubscribeResume(@PathVariable int resumeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        subscribeService.removeResumeSubscription(resumeId, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil(null));
    }
}
