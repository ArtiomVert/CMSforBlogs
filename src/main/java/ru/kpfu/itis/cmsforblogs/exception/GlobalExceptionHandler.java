package ru.kpfu.itis.cmsforblogs.exception;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleUserNotFoundException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());
        request.setAttribute(RequestDispatcher.ERROR_MESSAGE, ex.getMessage());
        request.setAttribute(RequestDispatcher.ERROR_EXCEPTION, ex);
        return "forward:/error";
    }

}
