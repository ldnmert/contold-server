package com.contold.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contold.dto.DisplayUserDTO;
import com.contold.entity.User;
import com.contold.mapper.UserMapper;
import com.contold.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DisplayUserDTO> getUserById(@PathVariable Long id) {

		User user = userService.findById(id);
		
		DisplayUserDTO displayUserDTO = UserMapper.toDTO(user);
		
		

		return ResponseEntity.ok(displayUserDTO);

	}
		
	
}
