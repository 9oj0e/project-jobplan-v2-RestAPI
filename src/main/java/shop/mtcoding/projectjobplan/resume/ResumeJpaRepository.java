package shop.mtcoding.projectjobplan.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResumeJpaRepository extends JpaRepository<Resume, Integer> {
    @Query("SELECT r FROM Resume r WHERE r.user.id = :id ORDER BY r.id DESC")
    Optional<List<Resume>> findByUserId(@Param("id") int id);

    @Query("SELECT r FROM Resume r JOIN FETCH r.user ORDER BY r.id DESC")
    Optional<List<Resume>> findAllJoinUser();

    @Query("SELECT r FROM Skill s JOIN Resume r ON s.user.id = r.user.id where s.name =:skill ORDER BY r.id DESC")
    Optional<List<Resume>> findAllJoinUserWithSkill(String skill);

    @Query("SELECT r from Skill s join Resume r on s.user.id = r.user.id where s.user.address LIKE %:address% ORDER BY r.id DESC")
    Optional<List<Resume>> findAllJoinUserWithAddress(String address);

    @Query("SELECT r from Skill s join Resume r on s.user.id = r.user.id where r.user.name like %:keyword% or r.title like %:keyword% or r.content like %:keyword% ORDER BY r.id DESC")
    Optional<List<Resume>> findAllJoinUserWithKeyword(String keyword);
}