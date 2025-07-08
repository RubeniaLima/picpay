package com.rubenialima.picpay.controller;

import com.rubenialima.picpay.exception.PicpayException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicpayException.class)
    public ProblemDetail handlePicPayException(PicpayException e){
        return e.toProblemDetail();
    }
}
