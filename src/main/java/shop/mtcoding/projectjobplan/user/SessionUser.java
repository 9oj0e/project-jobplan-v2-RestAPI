package shop.mtcoding.projectjobplan.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class SessionUser {
    // 앞으로 외부에 노출되는 데이터는 SessionUser, DB에 전달될 데이터는 User
    private Integer id;
    // 회원 정보
    private String username;
//    private String password; // 조회 되면 안되는 데이터
    // 개인 정보
//    private String name;
//    private String birthdate;
//    private Character gender; // 'M' or 'F'
//    private String phoneNumber;
//    private String address;
//    private String email;
    // 학력 정보
//    private String educationLevel; // 고졸/초대졸/대졸
//    private String schoolName;
//    private String major;
    // 회사 정보
    private Boolean isEmployer; // 사업자인지 userId, employerId
//    private String employerIdNumber; // 사업자번호
//    private String businessName; // 기업이름
    private Timestamp createdAt;

    @Builder
    public SessionUser(int id, String username, boolean isEmployer) {
        this.id = id;
        this.username = username;
//        this.name = user.getName();
//        this.birthdate = user.getBirthdate();
//        this.gender = user.getGender();
//        this.phoneNumber = user.getPhoneNumber();
//        this.address = user.getAddress();
//        this.email = user.getEmail();
//        this.educationLevel = user.getEducationLevel();
//        this.schoolName = user.getSchoolName();
//        this.major = user.getMajor();
        this.isEmployer = isEmployer;
//        this.employerIdNumber = user.getEmployerIdNumber();
//        this.businessName = user.getBusinessName();
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.isEmployer = user.getIsEmployer();
        this.createdAt = user.getCreatedAt();
    }
}
