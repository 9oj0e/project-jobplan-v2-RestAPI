package shop.mtcoding.projectjobplan.rating;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "rating_tb")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rater_id")
    private User rater; // 평가자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private User subject; // 피평가자

    @Column(nullable = false)
    private Double rating;

    // 이력서 주인이 공고를 보고 공고 주인을 평가
    public Rating(User sessionUser, Board board, Integer rating) {
        this.rater = sessionUser;
        this.subject = board.getUser();
        this.rating = Double.valueOf(rating);
    }


    // 공고 주인이 이력서를 보고 이력서 주인을 평가
    public Rating(User sessionUser, Resume resume, Integer rating) {
        this.rater = sessionUser;
        this.subject = resume.getUser();
        this.rating = Double.valueOf(rating);
    }

    @CreationTimestamp
    private Timestamp createdAt;

    /*
    private Boolean isRater;
    public Boolean getIsRater(Integer sessionUserId) { // 평가 기록이 있는지.
        return sessionUserId == this.raterId ? true : false;
    }
    */
}
