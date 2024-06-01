	package com.contold.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contold.dto.DisplayUserDTO;
import com.contold.entity.User;
import com.contold.exception.UnauthorizedAccessException;
import com.contold.mapper.FollowMapper;
import com.contold.mapper.UserMapper;
import com.contold.repository.FollowRepository;
import com.contold.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	private final FollowRepository followRepository;

	public UserController(UserService userService, FollowRepository followRepository) {
		this.userService = userService;
		this.followRepository = followRepository;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DisplayUserDTO> getUserById(@PathVariable Long id, Authentication auth) {

		User user2 = userService.findByUsername(auth.getName());
		
		System.out.println(FollowMapper.fromEntityToDisplayFollowingDTO(user2.getFollowing()));

		if (followRepository.existsByFollowerIdAndFollowedId(user2.getId(), id) == 1 || user2.getId() == id) {
			
			User user = userService.findById(id);

			DisplayUserDTO displayUserDTO = UserMapper.toDTO(user);

			return ResponseEntity.ok(displayUserDTO);

			
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
		
	
}
