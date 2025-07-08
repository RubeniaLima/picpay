package com.rubenialima.picpay.repository;

import com.rubenialima.picpay.entity.Wallet;
import com.rubenialima.picpay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
