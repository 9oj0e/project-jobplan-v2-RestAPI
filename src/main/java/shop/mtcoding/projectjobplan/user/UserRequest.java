package shop.mtcoding.projectjobplan.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class UserRequest {

    @Data // 회원가입
    public static class JoinDTO {
        // 회원 정보
        @Size(min = 3, max = 10, message = "유저네임은 3자 미만,10자를 초과할 수 없습니다.")
        @NotEmpty
        private String username;
        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;
        // 개인 정보
        private String name;
        private String birthdate;
        private Character gender; // 'M' or 'F'
        private String phoneNumber;
        private String address;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        // 학력 정보
        private String educationLevel; // 고졸/초대졸/대졸
        private String schoolName;
        private String major;
        // 회사 정보
        private Boolean isEmployer; // 사업자인지 userId, employerId
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름
    }

    @Data // 로그인
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data // 회원정보 수정
    public static class UpdateDTO {
        // 회원 정보
        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;
        // 개인 정보
        @Pattern(regexp = "^M|F$")
        private Character gender;
        @Min(value = 9, message = "유효한 번호를 입력하세요.")
        @Max(value = 11, message = "유효한 번호를 입력하세요.")
        @Pattern(regexp = "[0-9]", message = "번호만 입력해주세요.")
        private String phoneNumber;
        private String address;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        // 이력서정보
        private String schoolName;
        private String major;
        @Pattern(regexp = "^고졸|초대졸|대졸$")
        private String educationLevel; // 고졸/초대졸/대졸
        // 회사 정보
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름
    }

    @Data // 스킬 추가하기
    public static class SkillDTO {
        private List<@Pattern(
                regexp = "^(C|Java|Python|Linux|MySQL|Spring" +
                        "|HTML|javaScript|jQuery|AWS|JSP" +
                        "|Flutter|React|Node.js|Vue.js" +
                        "|Swift|Kotlin)$"
        ) String> skill = new ArrayList<>();
    }

    @Data
    public static class PicDTO {
        private MultipartFile imgFile;
    }
}
