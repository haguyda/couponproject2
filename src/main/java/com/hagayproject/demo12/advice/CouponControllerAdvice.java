package com.hagayproject.demo12.advice;



import com.hagayproject.demo12.exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponControllerAdvice {

    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleException(Exception e) {
        return new ErrDetails(e.getMessage());
    }
}