package shop.mtcoding.projectjobplan.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.skill.Skill;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 회원 정보
    private String username;
    private String password;
    // 개인 정보
    private String name;
    private String birthdate;
    private Character gender; // 'M' or 'F'
    private String phoneNumber;
    private String address;
    private String email;
    // 학력 정보
    private String educationLevel; // 고졸/초대졸/대졸
    private String schoolName;
    private String major;
    // 회사 정보
    private Boolean isEmployer; // 사업자인지 userId, employerId
    private String employerIdNumber; // 사업자번호
    private String businessName; // 기업이름
    // 참조 Entity
    @OrderBy("id desc")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Board> boards = new ArrayList<>();
    @OrderBy("id desc")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Resume> resumes = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Skill> skills = new ArrayList<>();
    // 사진
    private String imgFilename;

    @CreationTimestamp
    private Timestamp createdAt;

    public User(UserRequest.JoinDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.password = requestDTO.getPassword();
        this.name = requestDTO.getName();
        this.birthdate = requestDTO.getBirthdate();
        this.gender = requestDTO.getGender();
        this.phoneNumber = requestDTO.getPhoneNumber();
        this.address = requestDTO.getAddress();
        this.email = requestDTO.getEmail();
        this.educationLevel = requestDTO.getEducationLevel();
        this.schoolName = requestDTO.getSchoolName();
        this.major = requestDTO.getMajor();
        this.isEmployer = requestDTO.getIsEmployer();
        this.employerIdNumber = requestDTO.getEmployerIdNumber();
        this.businessName = requestDTO.getBusinessName();
    }

    public void update(UserRequest.UpdateDTO requestDTO) {
        this.password = requestDTO.getPassword();
        this.gender = requestDTO.getGender();
        this.phoneNumber = requestDTO.getPhoneNumber();
        this.address = requestDTO.getAddress();
        this.email = requestDTO.getEmail();
        this.educationLevel = requestDTO.getEducationLevel();
        this.schoolName = requestDTO.getSchoolName();
        this.major = requestDTO.getMajor();
        this.employerIdNumber = requestDTO.getEmployerIdNumber();
        this.businessName = requestDTO.getBusinessName();
    }

    @Builder
    public User(int id, String username, boolean isEmployer) {
        this.id = id;
        this.username = username;
        this.isEmployer = isEmployer;
    }

    public void picPost(String imgFilename) {
        setImgFilename(imgFilename);
    }
}
