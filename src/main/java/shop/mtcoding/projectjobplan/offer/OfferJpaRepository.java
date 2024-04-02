package shop.mtcoding.projectjobplan.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferJpaRepository extends JpaRepository<Offer, Integer> {

    // (기업) 모든 제안 현황
    @Query("SELECT o FROM Offer o WHERE o.board.user.id = :boardUserId")
    List<Offer> findByBoardUserId(@Param("boardUserId") int boardUserId);

    // (기업) 공고별 제안 현황
    @Query("SELECT o FROM Offer o WHERE o.board.id = :boardId")
    List<Offer> findByBoardId(@Param("boardId") int boardId);

    // (개인) 모든 제안 현황
    @Query("SELECT a FROM Offer a WHERE a.resume.user.id = :resumeUserId")
    List<Offer> findByResumeUserId(@Param("resumeUserId") int resumeUserId);

    // (개인) 이력서별 제안 현황
    @Query("SELECT a FROM Offer a WHERE a.resume.id = :resumeId")
    List<Offer> findByResumeId(@Param("resumeId") int resumeId);
}
