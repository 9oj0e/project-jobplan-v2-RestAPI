package shop.mtcoding.projectjobplan.user;

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

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/join") // 회원가입
    public ResponseEntity<?> join(@Valid @RequestBody UserRequest.JoinDTO requestDTO, Errors errors) {
        User sessionUser = userService.createUser(requestDTO);

        return ResponseEntity.ok(new ApiUtil(sessionUser));
    }

    @PostMapping("/login") // 로그인 sign in
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        String jwt = userService.getUser(requestDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer" + jwt).body(new ApiUtil<>(null));
    }

    @GetMapping("/logout") // 로그아웃 sign out
    public ResponseEntity<?> logout() {
        session.invalidate();

        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PutMapping("/api/users/{userId}") // 회원 정보 수정
    public ResponseEntity<?> update(@PathVariable Integer userId, @Valid @RequestBody UserRequest.UpdateDTO requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.setUser(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil(newSessionUser));
    }

    @GetMapping({"/api/users/{userId}", // 회원 정보 보기
            "/api/users/{userId}/boards", // (기업) 지원자 현황 보기 + 제안 현황
            "/api/users/{userId}/boards/{boardId}", // (기업) 공고별 지원자 보기 + 제안 현황
            "/api/users/{userId}/resumes", // (개인) 지원 현황 보기 + 제안 현황
            "/api/users/{userId}/resumes/{resumeId}"}) // (개인) 이력서별 지원 현황 보기 + 제안 현황
    public ResponseEntity<?> profile(
            @PathVariable Integer userId,
            @PathVariable(required = false) Integer boardId,
            @PathVariable(required = false) Integer resumeId,
            @PageableDefault(size = 3) Pageable pageable,
            HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.ProfileDTO profileDTO = userService.getUser(sessionUser.getId(), boardId, resumeId, pageable);
        request.setAttribute("profileDTO", profileDTO);

        return ResponseEntity.ok(new ApiUtil(profileDTO));
    }

    @PostMapping("/api/users/{userId}/skills") // 스킬 추가, 수정 및 삭제
    public ResponseEntity<?> skillAdd(@PathVariable int userId, @RequestBody UserRequest.SkillDTO requestDTO) {
        List<UserResponse.SkillDTO> responseDTO = userService.createSkillList(requestDTO, userId);

        return ResponseEntity.ok(new ApiUtil<>(responseDTO));
    }
}
