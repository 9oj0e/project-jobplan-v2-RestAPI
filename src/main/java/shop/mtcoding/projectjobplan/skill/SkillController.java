package shop.mtcoding.projectjobplan.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class SkillController {
    private final SkillService skillService;

    @PostMapping("/users/{userId}/skill/add")
    public String add(@PathVariable int userId, SkillRequest.DTO requestDTO) {
        skillService.createSkillList(requestDTO, userId);

        return "redirect:/users/" + userId;
    }
}