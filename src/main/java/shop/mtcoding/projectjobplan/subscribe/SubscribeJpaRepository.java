package shop.mtcoding.projectjobplan.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscribeJpaRepository extends JpaRepository<Subscribe, Integer> {
    Optional<List<Subscribe>> findByUserId(int userId);

    Optional<Subscribe> findByBoardIdAndUserId(int boardId, int userId);

    Optional<Subscribe> findByResumeIdAndUserId(int resumeId, int userId);
}
