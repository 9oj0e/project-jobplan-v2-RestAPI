package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {

    @Query("SELECT b FROM Board b ORDER BY b.createdAt DESC limit :limit")
    Optional<List<Board>> findAllOrderByCreatedAtDesc(@Param("limit") int limit);

    @Query("SELECT b FROM Board b JOIN FETCH User u WHERE b.user.id = u.id ORDER BY b.id DESC")
    Optional<List<Board>> findAllByUserId(int userId);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u ORDER BY b.id DESC LIMIT :limit")
    Optional<List<Board>> findAllJoinUser(int limit);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u ORDER BY b.id DESC")
    Optional<List<Board>> findAllJoinUser();

    @Query("SELECT s.board FROM Skill s JOIN FETCH s.board.user WHERE s.name =:skill ORDER BY s.board.createdAt DESC")
    Optional<List<Board>> findAllJoinUserWithSkill(String skill);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u WHERE u.address LIKE %:address% ORDER BY b.createdAt DESC")
    Optional<List<Board>> findAllJoinUserWithAddress(String address);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword% OR u.businessName LIKE %:keyword% ORDER BY b.createdAt DESC")
    Optional<List<Board>> findAllJoinUserWithKeyword(String keyword);

    @Query("SELECT b FROM Board b WHERE b.user.id = :id ORDER BY b.id DESC")
    Optional<List<Board>> findByUserId(@Param("id") int id);
}
