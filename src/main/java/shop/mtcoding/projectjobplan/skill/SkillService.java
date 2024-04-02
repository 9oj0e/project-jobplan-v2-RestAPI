package shop.mtcoding.projectjobplan.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillJpaRepository skillJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public void createSkillList(SkillRequest.DTO dto, int userId) {
        User user = userJpaRepository.findById(userId).get();

        List<Skill> skillList = new ArrayList<>();
        for (String skillName : dto.getSkill()) {
            Skill skill = Skill.builder()
                    .user(user)
                    .name(skillName)
                    .build();
            skillList.add(skill);
        }
        // dto.getSkill().stream().forEach(s -> new Skill(user, s));
        List<Skill> skillFound = skillJpaRepository.findByUserId(userId).orElse(null);
        if (skillFound != null) {
            skillJpaRepository.deleteAll(skillFound);
        }

        skillJpaRepository.saveAll(skillList);
    }
}
