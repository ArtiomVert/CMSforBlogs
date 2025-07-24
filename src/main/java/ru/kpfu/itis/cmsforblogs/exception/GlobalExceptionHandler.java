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
        request.setAttribute("statusCode", HttpStatus.NOT_FOUND.value());
        request.setAttribute("errorMessage", ex.getMessage());
        request.setAttribute("exception", ex);
        return "forward:/error";
    }

}
