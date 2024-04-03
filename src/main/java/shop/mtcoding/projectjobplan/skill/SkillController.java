package shop.mtcoding.projectjobplan.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class SkillController {
    private final SkillService skillService;

    @PostMapping("/api/users/{userId}/skills")
    public ResponseEntity<?> add(@PathVariable int userId, SkillRequest.DTO requestDTO) {
        System.out.println(requestDTO);
        SkillResponse.DTO responseDTO = skillService.createSkillList(requestDTO, userId);

        return ResponseEntity.ok(new ApiUtil<>(responseDTO));
    }
}