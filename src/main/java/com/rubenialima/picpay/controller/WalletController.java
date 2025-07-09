package com.rubenialima.picpay.controller;

import com.rubenialima.picpay.controller.dto.CreateWalletDto;
import com.rubenialima.picpay.entity.Wallet;
import com.rubenialima.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody  @Valid  CreateWalletDto dto){
        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }
}
