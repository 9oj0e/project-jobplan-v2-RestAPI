package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.subscribe.Subscribe;
import shop.mtcoding.projectjobplan.user.SessionUser;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content; // cv, cover letter 자기소개서
    private String career; // 회사명+경력

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Entity 객체의 변수명 == FK의 주인
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Entity 객체의 변수명 == FK의 주인
    private List<Subscribe> subscribes = new ArrayList<>();

    public void update(ResumeRequest.UpdateDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.career = requestDTO.getCareer();
    }

    public Resume(ResumeRequest.PostDTO requestDTO, User user) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.career = requestDTO.getCareer();
        this.user = user;
    }
}
