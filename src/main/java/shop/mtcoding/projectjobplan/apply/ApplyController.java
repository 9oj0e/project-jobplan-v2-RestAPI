package shop.mtcoding.projectjobplan.apply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@RestController
public class ApplyController {
    private final HttpSession session;
    private final ApplyService applyService;

    // todo: getBoardAndResume @GetMapping("/boards/{boardId}/apply-form")
    @GetMapping("/api/boards/{boardId}/apply")
    public ResponseEntity<?> applyForm(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ApplyResponse.ApplyFormDTO applyFormDTO = applyService.getBoardAndResume(boardId, sessionUser);
        return ResponseEntity.ok(new ApiUtil(applyFormDTO));
    }

    @PostMapping("/api/boards/{boardId}/apply")
    public ResponseEntity<?> apply(@PathVariable int boardId, @RequestBody ApplyRequest.ApplyDTO requestDTO) {
        ApplyResponse.ApplyDTO applyDTO = applyService.createApply(requestDTO);

        return ResponseEntity.ok(new ApiUtil(applyDTO));
    }

    @PutMapping("/api/apply")
    public ResponseEntity<?> update(@RequestBody ApplyRequest.UpdateDTO requestDTO) { // 지원자 합격/불합격 처리
        User user = (User) session.getAttribute("sessionUser");
        ApplyResponse.UpdateDTO updateDTO = applyService.updateApply(requestDTO);

        return ResponseEntity.ok(new ApiUtil(updateDTO));
    }
}
