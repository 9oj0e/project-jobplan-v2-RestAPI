package shop.mtcoding.projectjobplan.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.SessionUser;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@RestController
public class ResumeController {
    private final HttpSession session;
    private final ResumeService resumeService;


    @PostMapping("/api/resumes") // 이력서 작성 action
    public ResponseEntity<?> post(@Valid @RequestBody ResumeRequest.PostDTO requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.SaveDTO resumeDTO = resumeService.createResume(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(resumeDTO));
    }

    @GetMapping("/api/resumes/{resumeId}")
    public ResponseEntity<?> detail(@PathVariable int resumeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        ResumeResponse.DetailDTO resumeDetail = resumeService.getResumeInDetail(resumeId, sessionUserId);

        return ResponseEntity.ok(new ApiUtil(resumeDetail));
    }

    //기업 메인
    @GetMapping("/api/resumes")
    public ResponseEntity<?> listings(HttpServletRequest request,
                                      @PageableDefault(size = 10) Pageable pageable,
                                      @RequestParam(value = "userid", required = false) Integer userid,
                                      @RequestParam(value = "skill", required = false) String skill,
                                      @RequestParam(value = "address", required = false) String address,
                                      @RequestParam(value = "keyword", required = false) String keyword) {
        ResumeResponse.ListingsDTO responseDTO = resumeService.getAllResume(pageable, userid, skill, address, keyword);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }


    @PutMapping("/api/resumes/{resumeId}") // 이력서수정
    public ResponseEntity<?> update(@PathVariable int resumeId, @Valid @RequestBody ResumeRequest.UpdateDTO requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.UpdateDTO resumeDTO = resumeService.setResume(resumeId, requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(resumeDTO));
    }

    @DeleteMapping("/api/resumes/{resumeId}")
    public ResponseEntity<?> delete(@PathVariable int resumeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        resumeService.removeResume(resumeId, sessionUser);

        return ResponseEntity.ok(new ApiUtil(null));
    }
}
