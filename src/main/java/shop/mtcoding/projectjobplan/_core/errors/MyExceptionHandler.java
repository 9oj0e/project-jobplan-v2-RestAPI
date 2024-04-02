package shop.mtcoding.projectjobplan._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding.projectjobplan._core.errors.exception.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(RuntimeException e){

        ApiUtil<?> apiUtil = new ApiUtil<>(400,e.getMessage()); // http body 내부의 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); // http header , http body
    }
    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(401,e.getMessage()); // http body 내부의 구성한 객체
        return new ResponseEntity<>(apiUtil,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(403,e.getMessage()); // http body 내부의 구성한 객체
        return new ResponseEntity<>(apiUtil,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(404,e.getMessage()); // http body 내부의 구성한 객체

        return new ResponseEntity<>(apiUtil,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(500,e.getMessage()); // http body 내부의 구성한 객체
        return new ResponseEntity<>(apiUtil,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}