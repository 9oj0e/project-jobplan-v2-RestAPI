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

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody UserRequest.JoinDTO requestDTO, Errors errors) {
        User sessionUser = userService.createUser(requestDTO);

        return ResponseEntity.ok(new ApiUtil(sessionUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        String jwt = userService.getUser(requestDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer" + jwt).body(new ApiUtil<>(null));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();

        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PutMapping("/api/users/{userId}")
    public ResponseEntity<?> update(@PathVariable Integer userId, @Valid @RequestBody UserRequest.UpdateDTO requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.setUser(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil(newSessionUser));
    }

    @GetMapping({"/api/users/{userId}",
            "/api/users/{userId}/boards",
            "/api/users/{userId}/boards/{boardId}",
            "/api/users/{userId}/resumes",
            "/api/users/{userId}/resumes/{resumeId}"})
    public ResponseEntity<?> profile(
            @PathVariable Integer userId,
            @PathVariable(required = false) Integer boardId,
            @PathVariable(required = false) Integer resumeId,
            @PageableDefault(size = 3) Pageable pageable,
            HttpServletRequest request) {
        // todo: NullPointException
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.ProfileDTO profileDTO = userService.getUser(sessionUser.getId(), boardId, resumeId, pageable);
        request.setAttribute("profileDTO", profileDTO);

        return ResponseEntity.ok(new ApiUtil(profileDTO));
    }
}
