package shop.mtcoding.projectjobplan.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingJpaRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.subject.id = :id")
    Optional<Double> findRatingAvgByUserId(@Param("id") Integer id);

    @Query("SELECT r FROM Rating r WHERE r.rater.id = :raterId AND r.subject.id = :subjectId")
    Optional<Rating> findByRaterIdAndSubjectId(@Param("raterId") int raterId, @Param("subjectId") int subjectId);
}
