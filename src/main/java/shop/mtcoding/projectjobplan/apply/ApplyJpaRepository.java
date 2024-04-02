package shop.mtcoding.projectjobplan.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyJpaRepository extends JpaRepository<Apply, Integer> {

    // (기업) 지원자 현황
    @Query("SELECT a FROM Apply a WHERE a.board.user.id = :userId")
    List<Apply> findByBoardUserId(@Param("userId") int userId);

    // (기업) 공고별 지원자 현황 (ByBoardIdAndUserId 삭제, 불필요한 참조)
    @Query("SELECT a FROM Apply a WHERE a.board.id = :boardId")
    List<Apply> findByBoardId(@Param("boardId") int boardId);

    // (개인) 지원 현황
    @Query("SELECT a FROM Apply a WHERE a.resume.user.id = :userId")
    List<Apply> findByResumeUserId(@Param("userId") int userId);

    // (개인) 공고별 지원 현황
    @Query("SELECT a FROM Apply a WHERE a.resume.id = :resumeId")
    List<Apply> findByResumeId(@Param("resumeId") int resumeId);
}
