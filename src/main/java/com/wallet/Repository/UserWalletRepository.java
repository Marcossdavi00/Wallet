package com.wallet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.Entity.UserWallet;

public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

}
