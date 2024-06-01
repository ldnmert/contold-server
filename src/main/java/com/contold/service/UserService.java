package com.contold.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contold.entity.User;
import com.contold.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
	}
	
	
	

	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
				
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void deleteById(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException("User not found with id " + id);
		}
	}

}
