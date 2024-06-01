package com.contold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contold.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
}
