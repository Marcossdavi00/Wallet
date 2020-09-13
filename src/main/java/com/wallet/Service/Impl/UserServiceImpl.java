package com.wallet.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.Entity.User;
import com.wallet.Repository.UserRepository;
import com.wallet.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public User save(User u) {
		// TODO Auto-generated method stub
		return repository.save(u);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmailEquals(email);
	}

}
