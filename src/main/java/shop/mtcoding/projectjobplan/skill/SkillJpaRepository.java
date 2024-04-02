package shop.mtcoding.projectjobplan.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillJpaRepository extends JpaRepository<Skill, Integer> {
    @Query("SELECT s FROM Skill s WHERE s.user.id = :userId")
    Optional<List<Skill>> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT s FROM Skill s WHERE s.board.id = :boardId")
    Optional<List<Skill>> findByBoardId(@Param("boardId") Integer boardId);
}