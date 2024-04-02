package shop.mtcoding.projectjobplan.apply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final HttpSession session;
    private final ApplyService applyService;

    @GetMapping("/boards/{boardId}/apply-form")
    public String applyForm(@PathVariable int boardId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ApplyFormDTO responseDTO = applyService.getBoardAndResume(boardId, sessionUser);
        request.setAttribute("applyForm", responseDTO);

        return "apply/apply-form";
    }

    @PostMapping("/boards/{boardId}/apply")
    public String apply(@PathVariable int boardId, ApplyRequest.ApplyDTO requestDTO) {
        applyService.createApply(requestDTO);

        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/apply/update")
    public String update(ApplyRequest.UpdateDTO requestDTO) { // 지원자 합격/불합격 처리
        User user = (User) session.getAttribute("sessionUser");
        applyService.updateApply(requestDTO);

        return "redirect:/users/" + user.getId();
    }
}
