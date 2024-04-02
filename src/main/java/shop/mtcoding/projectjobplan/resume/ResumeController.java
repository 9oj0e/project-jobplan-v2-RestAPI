package shop.mtcoding.projectjobplan.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final HttpSession session;
    private final ResumeService resumeService;

    @GetMapping("/resumes/main")
    public String main() {
        return "resume/main";
    }

    @GetMapping("/resumes/post-form") // 이력서 작성 폼
    public String postForm() {

        return "resume/post-form";
    }

    @PostMapping("/resumes/post") // 이력서 작성 action
    public String post(@Valid ResumeRequest.PostDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Resume resume = resumeService.createResume(requestDTO, sessionUser);

        return "redirect:/resumes/" + resume.getId();
    }

    @GetMapping("/resumes/{resumeId}")
    public String detail(@PathVariable int resumeId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        ResumeResponse.DetailDTO resumeDetail = resumeService.getResumeInDetail(resumeId, sessionUserId);
        request.setAttribute("resumeDetail", resumeDetail);

        return "resume/detail";
    }

    @GetMapping("/resumes")
    public String listings(HttpServletRequest request,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(value = "userid", required = false) Integer userid,
                           @RequestParam(value = "skill", required = false) String skill,
                           @RequestParam(value = "address", required = false) String address,
                           @RequestParam(value = "keyword", required = false) String keyword) {
        ResumeResponse.ListingsDTO responseDTO = resumeService.getAllResume(pageable, userid, skill, address, keyword);
        request.setAttribute("page", responseDTO);

        return "resume/listings";
    }

    /* modal로 대체
    @GetMapping("/resumes/{resumeId}/update-form") // 이력서수정폼
    public String updateForm(@PathVariable int resumeId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.UpdateFormDTO responseDTO = resumeService.getResume(resumeId, sessionUser);
        request.setAttribute("resume", responseDTO);

        return "resume/update-form";
    }
    */
    @PostMapping("/resumes/{resumeId}/update") // 이력서수정
    public String update(@PathVariable int resumeId,@Valid ResumeRequest.UpdateDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.setResume(resumeId, requestDTO, sessionUser);

        return "redirect:/resumes/" + resumeId;
    }

    @PostMapping("/resumes/{resumeId}/delete")
    public String delete(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.removeResume(resumeId, sessionUser);

        return "redirect:/users/" + sessionUser.getId();
    }
}
