package shop.mtcoding.projectjobplan.skill;

import lombok.Data;

import java.util.List;

public class SkillResponse {
    @Data
    public static class DTO {
        List<SkillDTO> skillList;

        public DTO(List<Skill> skills) {
            this.skillList = skills.stream().map(skill -> new SkillDTO(skill)).toList();
        }

        @Data
        public class SkillDTO {
            private Integer userId;
            private String skillName;

            public SkillDTO(Skill skill) {
                this.userId = skill.getUser().getId();
                this.skillName = skill.getName();
            }
        }
    }
}
