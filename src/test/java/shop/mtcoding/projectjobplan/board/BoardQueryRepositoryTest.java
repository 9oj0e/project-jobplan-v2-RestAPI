package shop.mtcoding.projectjobplan.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Import(BoardQueryRepository.class)
public class BoardQueryRepositoryTest {
    @Autowired
    BoardQueryRepository boardQueryRepository;
    @Autowired
    UserJpaRepository userJpaRepository;
    @Test
    public void findWithSkill_test() {
        // given
        User user = userJpaRepository.findById(6).get();
        // when
        List<Object[]> results = boardQueryRepository.findWithSkill(user.getSkills());
        // then
        System.out.println(results);
    }
}
