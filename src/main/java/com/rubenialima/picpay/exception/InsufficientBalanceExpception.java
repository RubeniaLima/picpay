package com.rubenialima.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceExpception extends PicpayException{

    @Override
    public ProblemDetail toProblemDetail() {

        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pd.setTitle("Insufficient balance");
        pd.setDetail("You cannot transfer a value bigger than your current balance");

        return pd;

    }
}
