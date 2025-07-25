package com.rubenialima.picpay.service;

import com.rubenialima.picpay.controller.dto.TransferDto;
import com.rubenialima.picpay.entity.Transfer;
import com.rubenialima.picpay.entity.Wallet;
import com.rubenialima.picpay.exception.InsufficientBalanceExpception;
import com.rubenialima.picpay.exception.TransferNotAllowedForWalletTypeException;
import com.rubenialima.picpay.exception.TransferNotAuthorizedException;
import com.rubenialima.picpay.exception.WalletNotFoundException;
import com.rubenialima.picpay.repository.TransferRepository;
import com.rubenialima.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

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

    @Transactional
    public Transfer  transfer( TransferDto transferDto) {
        var sender= walletRepository.findById(transferDto.payer())
                 .orElseThrow(()-> new WalletNotFoundException(transferDto.payer()));

        var receiver= walletRepository.findById(transferDto.payee())
                .orElseThrow((()-> new WalletNotFoundException(transferDto.payee())));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender,receiver,transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult =transferRepository.save(transfer);

        CompletableFuture.runAsync(() ->notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {
        if(!sender.isTransferAllowesForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }
        if(!sender.isBalanceEqualOrGreaterThan(transferDto.value())){
            throw new InsufficientBalanceExpception( );
        }

        if(! authorizationService.isAuthorized(transferDto)){
            throw new TransferNotAuthorizedException();
        }
    }
}
