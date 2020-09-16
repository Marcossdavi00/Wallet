package com.wallet.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.Entity.UserWallet;
import com.wallet.Repository.UserWalletRepository;
import com.wallet.Service.UserWalletService;

@Service
public class UserWalletImpl implements UserWalletService {

	@Autowired
	UserWalletRepository repository;
	@Override
	public UserWallet save(UserWallet uw) {
		
		return repository.save(uw);
	}

	
}
