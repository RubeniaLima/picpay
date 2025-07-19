package com.rubenialima.picpay.service;

import com.rubenialima.picpay.controller.dto.TransferDto;
import com.rubenialima.picpay.entity.Transfer;
import com.rubenialima.picpay.repository.TransferRepository;
import com.rubenialima.picpay.repository.WalletRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository, AuthorizationService authorizationService, NotificationService notificationService, WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }


    public Transfer  transfer( TransferDto dto) {

        return null;
    }
}
