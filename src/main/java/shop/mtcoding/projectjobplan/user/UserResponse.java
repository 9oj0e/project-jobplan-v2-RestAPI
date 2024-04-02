package shop.mtcoding.projectjobplan.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.offer.Offer;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;
import java.util.List;

public class UserResponse {

    @Data
    public static class UpdateFormDTO {
        // 회원 정보
        private Integer id;
        private String username;
        private String password;

        // 개인 정보
        private String name;
        private String birthdate;
        // private Character gender; // view에서 분기 처리 하려면.. JS필요
        private String phoneNumber;
        private String address;
        private String email;

        // 회사 정보
        private Boolean isEmployer;
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름

        public UpdateFormDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();

            if (user.getIsEmployer()) {
                this.isEmployer = user.getIsEmployer();
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
            }
        }
    }

    @Data
    public static class ProfileDTO {
        // 회원 정보
        private Integer id;
        private String username;
        private String password;
        // 개인 정보
        private String name;
        private String birthdate;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        // 평점
        private Double rating;
        // 학력 정보
        private String educationLevel;
        private String schoolName;
        private String major;
        // 기업 정보
        private Boolean isEmployer;
        private String employerIdNumber;
        private String businessName;
        // tech stack 기술
        private List<SkillDTO> skillList;
        private Boolean hasSkill;
        // 게시물 정보
        private Page<ResumeDTO> resumeList;
        private Page<BoardDTO> boardList;
        // 지원자 현황 및 지원 현황 & 제안 현황
        private Page<ApplyDTO> applyList;
        private Page<OfferDTO> offerList;
        // Integer applyCount = applyList.getSize(); // 지원자 및 지원 갯수
        // Integer offerCount = offerList.getSize(); // 제안 갯수

        private Boolean hasSkill() {
            if (this.skillList.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }

        public ProfileDTO(User user, List<Apply> applyList, List<Offer> offerList, Double rating, Pageable pageable) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            this.educationLevel = user.getEducationLevel();
            this.schoolName = user.getSchoolName();
            this.major = user.getMajor();
            this.rating = rating;
            if (user.getIsEmployer()) {
                this.isEmployer = user.getIsEmployer();
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
                List<BoardDTO> boardList = user.getBoards().stream().map(board -> new BoardDTO(board)).toList();
                this.boardList = PagingUtil.pageConverter(pageable, boardList);
            } else {
                List<ResumeDTO> resumeList = user.getResumes().stream().map(resume -> new ResumeDTO(resume)).toList();
                this.resumeList = PagingUtil.pageConverter(pageable, resumeList);
                this.skillList = user.getSkills().stream().map(skill -> new SkillDTO(skill.getName())).toList();
            }
            List<ApplyDTO> applies = applyList.stream().map(apply -> new ApplyDTO(apply)).toList();
            this.applyList = PagingUtil.pageConverter(pageable, applies); // todo : pagination?
            List<OfferDTO> offers = offerList.stream().map(offer -> new OfferDTO(offer)).toList();
            this.offerList = PagingUtil.pageConverter(pageable, offers);
        }

        public Double getRating() {
            return FormatUtil.numberFormatter(this.rating);
        }

        public class BoardDTO {
            private Integer id;
            private String title;
            private String field;
            private String position;
            private Timestamp openingDate;

            public BoardDTO(Board board) {
                this.id = board.getId();
                this.title = board.getTitle();
                this.field = board.getField();
                this.position = board.getPosition();
                this.openingDate = board.getOpeningDate();
            }

            public String getOpeningDate() {
                return FormatUtil.timeFormatter(this.openingDate);
            }

            public String getTitle() {
                return FormatUtil.stringFormatter(this.title);
            }
        }
        @Data
        public class ResumeDTO {
            private Integer id;
            private String title;
            private Timestamp createdAt;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
                this.createdAt = resume.getCreatedAt();
            }

            public String getTitle() {
                return FormatUtil.stringFormatter(this.title);
            }
            public String getOgTitle() {
                return this.title;
            }

            public String getCreatedAt() {
                return FormatUtil.timeFormatter(this.createdAt);
            }
        }
        @Data
        public class ApplyDTO {
            // 공고 정보
            private Integer resumeId;
            private String resumeTitle;
            private String applicantName;

            // 이력서 정보
            private Integer boardId;
            private String boardTitle;
            private String businessName;

            // 제안 정보
            private Integer id;
            private Boolean status;
            private String createdAt;

            public ApplyDTO(Apply apply) {
                this.resumeId = apply.getResume().getId();
                this.resumeTitle = apply.getResume().getTitle();
                this.applicantName = apply.getResume().getUser().getName();
                this.boardId = apply.getBoard().getId();
                this.boardTitle = apply.getBoard().getTitle();
                this.businessName = apply.getBoard().getUser().getBusinessName();
                this.id = apply.getId();
                this.createdAt = apply.getCreatedAt();
                this.status = apply.getStatus();
            }

            public String getResumeTitle() {
                return FormatUtil.stringFormatter(this.resumeTitle);
            }

            public String getBoardTitle() {
                return FormatUtil.stringFormatter(this.boardTitle);
            }

            public String getStatus() {
                try {
                    if (this.status) return "합격";
                    else if (!this.status) return "불합격";
                    else return null;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        @Data
        public class OfferDTO {
            // 이력서 정보
            private Integer resumeId;
            private String title;
            private String username;
            private String career;

            // 공고 정보
            private Integer boardId;
            private String boardTitle;
            private String position;
            private String field;
            private String businessName;

            // 제안 정보
            private Integer id;
            private Boolean status;
            private String createdAt;

            public OfferDTO(Offer offer) {
                this.resumeId = offer.getResume().getId();
                this.title = offer.getResume().getTitle();
                this.username = offer.getResume().getUser().getName();
                this.career = offer.getResume().getCareer();
                this.boardId = offer.getBoard().getId();
                this.boardTitle = offer.getBoard().getTitle();
                this.position = offer.getBoard().getPosition();
                this.field = offer.getBoard().getField();
                this.businessName = offer.getBoard().getUser().getBusinessName();
                this.id = offer.getId();
                this.status = offer.getStatus();
                this.createdAt = offer.getCreatedAt();
            }

            public String getTitle() {
                return FormatUtil.stringFormatter(this.title);
            }

            public String getBoardTitle() {
                return FormatUtil.stringFormatter(this.boardTitle);
            }

            public String getStatus() {
                try {
                    if (this.status) return "수락";
                    else if (!this.status) return "거절";
                    else return null;
                } catch (Exception e) {
                    return null;
                }
            }
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
    public static class UpdateDTO {
        private Integer id;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        private String schoolName;
        private String major;
        private String educationLevel;
        private String employerIdNumber;
        private String businessName;

        public UpdateDTO(User user) {
            this.id = user.getId();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            this.schoolName = user.getSchoolName();
            this.major = user.getMajor();
            this.educationLevel = user.getEducationLevel();
            this.employerIdNumber = user.getEmployerIdNumber();
            this.businessName = user.getBusinessName();
        }
    }
}

