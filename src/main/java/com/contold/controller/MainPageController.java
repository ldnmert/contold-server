package com.contold.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contold.dto.DisplayPostDTO;
import com.contold.entity.Post;
import com.contold.service.MainPageService;
import com.contold.service.UserService;

@RestController
@RequestMapping("/mainpage")
public class MainPageController {

	private final MainPageService mainPageService;
	
	
	
	public MainPageController(UserService userService, MainPageService mainPageService) {

		this.mainPageService = mainPageService;
	
	}


	@GetMapping
	public ResponseEntity<List<DisplayPostDTO>> getCurrentUsersMainPage(Authentication auth) {
		System.out.println("miniminiworldmini");
		return ResponseEntity.ok(mainPageService.getMainPageOfCurrentUser(auth));

	}
	
}
