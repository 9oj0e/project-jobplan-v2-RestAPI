package shop.mtcoding.projectjobplan._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.mtcoding.projectjobplan._core.interceptor.LoginInterceptor;

@Configuration // IoC
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/boards/{id:\\d+}", "/boards", "/", "/resumes/main", "/join/**", "/users/join-form/**");
    }
}