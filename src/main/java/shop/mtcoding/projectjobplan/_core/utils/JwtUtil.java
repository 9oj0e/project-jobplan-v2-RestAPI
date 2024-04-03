package shop.mtcoding.projectjobplan._core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.mtcoding.projectjobplan.user.SessionUser;
import shop.mtcoding.projectjobplan.user.User;

import java.util.Date;

public class JwtUtil {
    public static String create(User user) {
        String jwt = JWT.create()
                .withSubject("projectjobplan")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 48))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("isEmployer", user.getIsEmployer())
                .sign(Algorithm.HMAC512("metacoding")); // 나중에 환경 변수로 변경.
        System.out.println("jwt --------------------------");
        System.out.println(jwt);
        System.out.println("jwt --------------------------");

        return jwt;
    }

    public static SessionUser verify(String jwt){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("metacoding")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();
        boolean isEmployer = decodedJWT.getClaim("isEmployer").asBoolean();

        return new SessionUser(id, username, isEmployer);
    }
}
