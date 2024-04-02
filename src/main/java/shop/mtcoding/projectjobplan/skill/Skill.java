package shop.mtcoding.projectjobplan.skill;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "skill_tb")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    private User user; // 내가 갖고 있는 스킬

    @ManyToOne
    private Board board; // 공고에서 필요한 스킬 등록

    @Builder
    public Skill(String name, User user, Board board) {
        this.name = name;
        this.user = user;
        this.board = board;
    }

    public Skill(User user, String name) {
        this.user = user;
        this.name = name;
    }
}
