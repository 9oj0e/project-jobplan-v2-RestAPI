package shop.mtcoding.projectjobplan.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    /* modal로 대체
    @GetMapping("/users/join-type")
    public String joinType() {

        return "user/join-type";
    }
    */
    @PostMapping("/users/join-form")
    public String joinForm(boolean isEmployer, HttpServletRequest request) {
        request.setAttribute("isEmployer", isEmployer);

        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO requestDTO, Errors errors) {
        User sessionUser = userService.createUser(requestDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    /* modal로 대체
    @GetMapping("/login-form")
    public String loginForm() {

        return "/user/login-form";
    }
    */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        User sessionUser = userService.getUser(requestDTO);
        session.setAttribute("sessionUser", sessionUser);

        return ResponseEntity.ok(new ApiUtil(null));
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }

    /* modal 로 대체
    @GetMapping("/users/{userId}/update-form")
    public String updateForm(@PathVariable Integer userId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.UpdateFormDTO user = userService.getUser(sessionUser.getId());
        request.setAttribute("user", user);

        return "user/update-form";
    }
    */
    @PostMapping("/users/{userId}/update")
    public String update(@PathVariable Integer userId, @Valid UserRequest.UpdateDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.setUser(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return "redirect:/users/" + userId;
    }

    @GetMapping({"/users/{userId}",
            "/users/{userId}/boards",
            "/users/{userId}/boards/{boardId}",
            "/users/{userId}/resumes",
            "/users/{userId}/resumes/{resumeId}"})
    public String profile(
            @PathVariable Integer userId,
            @PathVariable(required = false) Integer boardId,
            @PathVariable(required = false) Integer resumeId,
            @PageableDefault(size = 3) Pageable pageable,
            HttpServletRequest request) {
        // todo: NullPointException
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.ProfileDTO profileDTO = userService.getUser(sessionUser.getId(), boardId, resumeId, pageable);
        request.setAttribute("profileDTO", profileDTO);

        return "user/profile";
    }
}
