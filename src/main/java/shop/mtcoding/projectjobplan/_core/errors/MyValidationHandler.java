package shop.mtcoding.projectjobplan._core.errors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception400;

@Aspect
@Component
public class MyValidationHandler {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validationHandler(JoinPoint jp){
        Object[] args = jp.getArgs();
        for(Object arg : args){
            if(arg instanceof Errors){
                Errors errors = (Errors) arg;
                if(errors.hasErrors()){
                    for(FieldError error : errors.getFieldErrors()){
                        throw new Exception400(error.getDefaultMessage()+ " : "+error.getField());
                    }
                }
            }
        }

    }
}
