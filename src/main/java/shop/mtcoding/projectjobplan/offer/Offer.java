package shop.mtcoding.projectjobplan.offer;

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
@Table(name = "offer_tb")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    private Boolean status;
    @CreationTimestamp
    private Timestamp createdAt;

    public Offer(Resume resume, Board board) {
        this.resume = resume;
        this.board = board;
    }

    public void update(OfferRequest.UpdateDTO requestDTO) {
        this.status = requestDTO.getStatus();
    }

    public String getCreatedAt() {
        return FormatUtil.timeFormatter(createdAt);
    }
}
