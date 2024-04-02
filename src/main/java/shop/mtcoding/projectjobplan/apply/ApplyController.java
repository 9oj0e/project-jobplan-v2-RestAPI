package shop.mtcoding.projectjobplan.apply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@RestController
public class ApplyController {
    private final HttpSession session;
    private final ApplyService applyService;

    // todo: getBoardAndResume @GetMapping("/boards/{boardId}/apply-form")

    @PostMapping("/api/boards/{boardId}/apply")
    public String apply(@PathVariable int boardId, ApplyRequest.ApplyDTO requestDTO) {
        applyService.createApply(requestDTO);

        return "redirect:/boards/" + boardId;
    }

    @PutMapping("/api/applies")
    public String update(ApplyRequest.UpdateDTO requestDTO) { // 지원자 합격/불합격 처리
        User user = (User) session.getAttribute("sessionUser");
        applyService.updateApply(requestDTO);

        return "redirect:/users/" + user.getId();
    }
}
