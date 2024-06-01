package com.contold.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contold.entity.User;
import com.contold.repository.UserRepository;
import com.contold.security.AppUserDetails;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return org.springframework.security.core.userdetails.User.builder().username(userEntity.getUsername())
				.password(userEntity.getPassword()).roles(userEntity.getRole().toString()).build();
		
//		return new AppUserDetails(userEntity);
		

	}
}