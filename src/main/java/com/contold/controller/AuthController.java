package com.contold.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contold.dto.CreateUserDTO;
import com.contold.entity.User;
import com.contold.mapper.UserMapper;
import com.contold.repository.UserRepository;

@RestController
public class AuthController {
	private final BCryptPasswordEncoder passwordEncoder;
	private final UserRepository UserRepository;


	public AuthController(BCryptPasswordEncoder passwordEncoder, UserRepository UserRepository) {
		this.passwordEncoder = passwordEncoder;
		this.UserRepository = UserRepository;
	}

	
	@PostMapping("/sign-up")
	public ResponseEntity<String> signUp(@RequestBody CreateUserDTO createUserDTO) {
		if(createUserDTO.getRole() == null || createUserDTO.getRole().isEmpty())
			createUserDTO.setRole("STANDARD");
		User user = UserMapper.toEntity(createUserDTO);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserRepository.save(user);
		return new ResponseEntity<>("Kayit olusturuldu.", HttpStatus.CREATED);

	}
	
	@PostMapping("/aa")
	public String ww() {
		System.out.println("selam");
		return "ff";
	}
	

}
