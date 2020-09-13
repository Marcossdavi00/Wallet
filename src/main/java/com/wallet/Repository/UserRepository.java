package com.wallet.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.Entity.User;

@SuppressWarnings("rawtypes")
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmailEquals(String email);

}
