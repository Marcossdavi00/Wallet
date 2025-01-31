package com.wallet.Service;

import java.util.Optional;

import com.wallet.Entity.User;

public interface UserService {

	User save(User u);
	
	Optional<User> findByEmail(String email);
}
