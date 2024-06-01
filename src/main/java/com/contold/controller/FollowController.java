package com.contold.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contold.service.FollowService;

@RestController
@RequestMapping("/follow")
public class FollowController {

	
	private final FollowService followService;
	
	public FollowController(FollowService followService) {
		
		this.followService = followService;
	}
	
	@PostMapping("/{followerId}/request/{followedId}")
	public ResponseEntity<String> getFollowRequest(@PathVariable Long followerId, @PathVariable Long followedId){
		followService.sendFollowRequest(followerId, followedId);
        return ResponseEntity.status(HttpStatus.OK).body("Takip isteği başarıyla gönderildi.");
	}
	
}
