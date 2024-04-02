package shop.mtcoding.projectjobplan.resume;

import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

public class ResumeDTO {

    private Integer id;
    private String title;
    private String content; // cv, cover letter 자기소개서
    private String career; // 회사명+경력
    private User user;
    private Timestamp createdAt;

    public ResumeDTO(Resume resume) {
        this.id = resume.getId();
        this.title = resume.getTitle();
        this.content = resume.getContent();
        this.career = resume.getCareer();
        this.user = resume.getUser();
        this.createdAt = resume.getCreatedAt();
    }

    public ResumeDTO(Integer id, String title, String content, String career, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.career = career;
        this.user = user;
        this.createdAt = createdAt;
    }
}
