package shop.mtcoding.projectjobplan._core.utills;

import org.junit.jupiter.api.Test;
import shop.mtcoding.projectjobplan._core.utils.JwtUtil;
import shop.mtcoding.projectjobplan.user.User;

public class JwtUtilTest {
    @Test
    public void create_test() {
        // given
        User user = User.builder()
                .id(12)
                .username("user7")
                .isEmployer(false)
                .build();
        // when
        String jwt = JwtUtil.create(user);
        // then
        System.out.println(jwt);
    }

    @Test
    public void verify_test() {
        // given
        String jwt = null; // 값 긁어와서..
        // when
        JwtUtil.verify(jwt);
        // then
    }
}
