package com.rubenialima.picpay.service;

import com.rubenialima.picpay.client.AuthorizationClient;
import com.rubenialima.picpay.controller.dto.TransferDto;
import com.rubenialima.picpay.exception.PicpayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer){

        var resp = authorizationClient.isAuthorized();

        if(resp.getStatusCode().isError()){
            throw new PicpayException();
        }
        return resp.getBody().authorized();
    }
}
