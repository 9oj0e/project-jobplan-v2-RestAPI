package shop.mtcoding.projectjobplan.resume;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

public class ResumeResponse {

    @Data
    public static class UpdateFormDTO {
        // 이력서 정보
        private Integer id; // resumeId
        private String title;
        private String career;
        private String content;
        // 유저 정보
        private String name;
        private String birthdate;
        private String phoneNumber;
        private String address;
        private String email;
        private String educationLevel; // 학력
        private String schoolName;
        private String major; // 전공
        private List<SkillDTO> skillList; // 보유 스킬

        public UpdateFormDTO(Resume resume) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.content = resume.getContent();
            this.career = resume.getCareer();
            this.name = resume.getUser().getName();
            this.birthdate = resume.getUser().getBirthdate();
            this.phoneNumber = resume.getUser().getPhoneNumber();
            this.address = resume.getUser().getAddress();
            this.email = resume.getUser().getEmail();
            this.educationLevel = resume.getUser().getEducationLevel();
            this.schoolName = resume.getUser().getSchoolName();
            this.major = resume.getUser().getMajor();
            this.skillList = resume.getUser().getSkills().stream().map(skill -> new SkillDTO(skill.getName())).toList();
        }

        @Data
        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }
    }

    @Data
    public static class DetailDTO {
        // 이력서 정보
        private Integer id;
        private String career; // 회사명+경력
        private String title;
        private String content; // cv, cover letter 자기소개서
        // 회원 정보
        private Integer userId;
        private String username;
        private String name;
        private String birthdate;
        private String phoneNumber;
        private String address;
        private String email;
        private String educationLevel; // 고졸/초대졸/대졸
        private String schoolName;
        private String major; // 전공
        private List<SkillDTO> skillList; // 보유 스킬
        // 기타 정보
        private Double rating; // 평점
        private boolean isResumeOwner; // 이력서 주인 여부
        private boolean hasSubscribed; // 구독 여부
        private boolean hasRated; // 평가 여부

        public DetailDTO(Resume resume, Double rating, boolean isResumeOwner, boolean hasSubscribed, boolean hasRated) {
            this.id = resume.getId();
            this.career = resume.getCareer();
            this.title = resume.getTitle();
            this.content = resume.getContent();
            this.userId = resume.getUser().getId();
            this.username = resume.getUser().getUsername();
            this.name = resume.getUser().getName();
            this.birthdate = resume.getUser().getBirthdate();
            this.address = resume.getUser().getAddress();
            this.phoneNumber = resume.getUser().getPhoneNumber();
            this.email = resume.getUser().getEmail();
            this.educationLevel = resume.getUser().getEducationLevel();
            this.schoolName = resume.getUser().getSchoolName();
            this.major = resume.getUser().getMajor();
            this.skillList = resume.getUser().getSkills().stream().map(skill -> new SkillDTO(skill.getName())).toList();
            this.rating = rating;
            this.isResumeOwner = isResumeOwner;
            this.hasSubscribed = hasSubscribed;
            this.hasRated = hasRated;
        }

        @Data
        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }

        public Double getRating() {
            return FormatUtil.numberFormatter(this.rating);
        }
    }

    @Data
    public static class ListingsDTO {
        Page<ResumeDTO> resumeList;
        List<Integer> pageList;
        List<UserDTO> userList;
        String keyword;
        String skill;
        String address;

        public ListingsDTO(Pageable pageable, List<Resume> resumes, List<User> users, String skill, String address, String keyword) {
            List<ResumeDTO> resumeList = resumes.stream().map(resume -> new ResumeDTO(resume)).toList();
            this.resumeList = PagingUtil.pageConverter(pageable, resumeList);
            this.pageList = PagingUtil.getPageList(this.resumeList);
            this.userList = users.stream().map(user -> new UserDTO(user)).toList();
            this.skill = skill;
            this.address = address;
            this.keyword = keyword;
        }

        @Data
        public class UserDTO {
            private int id;
            private String name;
            private String schoolName;

            public UserDTO(User user) {
                this.id = user.getId();
                this.name = user.getName();
                this.schoolName = user.getSchoolName();
            }
        }

        @Data
        public class ResumeDTO {
            // resume_tb
            private Integer id;
            private String career;
            private String title;

            // user_tb
            private String name;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.career = resume.getCareer();
                this.title = resume.getTitle();
                this.name = resume.getUser().getName();
            }
        }
    }
}