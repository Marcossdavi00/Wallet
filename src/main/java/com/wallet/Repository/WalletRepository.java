package com.wallet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.Entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
