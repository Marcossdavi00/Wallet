package com.wallet.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.wallet.Entity.Wallet;
import com.wallet.Repository.WalletRepository;
import com.wallet.Service.WalletService;

@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	private WalletRepository repository;
	@Override
	public Wallet save(Wallet w) {
		// TODO Auto-generated method stub
		return repository.save(w);
	}

}
