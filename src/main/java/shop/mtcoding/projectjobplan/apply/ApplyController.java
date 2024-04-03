package shop.mtcoding.projectjobplan.apply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.SessionUser;

@RequiredArgsConstructor
@RestController
public class ApplyController {
    private final HttpSession session;
    private final ApplyService applyService;

    @GetMapping("/api/boards/{boardId}/applies")
    public ResponseEntity<?> applyForm(@PathVariable int boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ApplyResponse.ApplyFormDTO applyFormDTO = applyService.getBoardAndResume(boardId, sessionUser);

        return ResponseEntity.ok(new ApiUtil(applyFormDTO));
    }

    @PostMapping("/api/boards/{boardId}/applies")
    public ResponseEntity<?> apply(@PathVariable int boardId, @RequestBody ApplyRequest.ApplyDTO requestDTO) {
        ApplyResponse.ApplyDTO applyDTO = applyService.createApply(requestDTO);

        return ResponseEntity.ok(new ApiUtil(applyDTO));
    }

    @PutMapping("/api/users/{userId}/applies") // 지원자 합격/불합격 처리
    public ResponseEntity<?> update(@PathVariable int userId, @RequestBody ApplyRequest.UpdateDTO requestDTO) {
        session.getAttribute("sessionUser");
        ApplyResponse.UpdateDTO updateDTO = applyService.updateApply(requestDTO);

        return ResponseEntity.ok(new ApiUtil(updateDTO));
    }
}
