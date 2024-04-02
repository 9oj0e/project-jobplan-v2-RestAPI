package shop.mtcoding.projectjobplan.apply;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "apply_tb")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;
    // resume_id, resume_user_id
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    // board_id, board_user_id
    private Boolean status;
    @CreationTimestamp
    private Timestamp createdAt;

    public Apply(Resume resume, Board board) {
        this.resume = resume;
        this.board = board;
        // this.status = null;
        // this.createdAt = new Timestamp(System.currentTimeMillis()); // createdAt이 없어서 null. 애러 해결. (김성재)
    }

    public void update(ApplyRequest.UpdateDTO requestDTO) {
        this.status = requestDTO.getStatus();
    }

    public String getCreatedAt() {
        return FormatUtil.timeFormatter(createdAt);
    }
}
