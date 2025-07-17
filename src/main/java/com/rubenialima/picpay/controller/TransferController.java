package com.rubenialima.picpay.controller;

import com.rubenialima.picpay.service.TransferService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


}
