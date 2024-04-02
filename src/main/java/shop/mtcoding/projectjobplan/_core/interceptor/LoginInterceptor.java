package shop.mtcoding.projectjobplan._core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception401;
import shop.mtcoding.projectjobplan.user.User;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");
        HttpSession session = request.getSession();

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception401("로그인 하셔야 해요");
        }
        return true;
    }
}