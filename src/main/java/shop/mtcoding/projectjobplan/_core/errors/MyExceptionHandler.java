package shop.mtcoding.projectjobplan._core.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.projectjobplan._core.errors.exception.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception400 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage()); // http body -> 구성한 객체

        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); // http body, http header
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(Exception401 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage()); // http body -> 구성한 객체

        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED); // http body, http header
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(Exception401 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage()); // http body -> 구성한 객체

        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN); // http body, http header
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(Exception401 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage()); // http body -> 구성한 객체

        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND); // http body, http header
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(Exception401 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage()); // http body -> 구성한 객체

        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR); // http body, http header
    }
}