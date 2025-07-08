package com.rubenialima.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PicpayException extends RuntimeException{

    public ProblemDetail toProblemDetail(){
        var pd= ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setDetail("PicPay internal server error");

        return pd;
    }
}
