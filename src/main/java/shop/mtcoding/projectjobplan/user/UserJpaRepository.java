package shop.mtcoding.projectjobplan.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    // 암호화시, username으로 찾기
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u LEFT JOIN u.skills s GROUP BY u.id ORDER BY COUNT(s) DESC LIMIT 5")
    Optional<List<User>> findUsersWithMostSkills();
}
