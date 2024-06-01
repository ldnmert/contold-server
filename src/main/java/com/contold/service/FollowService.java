package com.contold.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.contold.entity.Follow;
import com.contold.entity.User;
import com.contold.repository.FollowRepository;
import com.contold.repository.UserRepository;

@Service
public class FollowService {

	private final UserRepository userRepository;

	private final FollowRepository followRepository;

	public FollowService(UserRepository userRepository, FollowRepository followRepository) {

		this.userRepository = userRepository;
		this.followRepository = followRepository;
	}

	public void sendFollowRequest(Long followerId, Long followedId) {

		User follower = userRepository.findById(followerId)
				.orElseThrow(() -> new IllegalArgumentException("Follower not found"));
		User followed = userRepository.findById(followedId)
				.orElseThrow(() -> new IllegalArgumentException("Followed not found"));

//	        Follow existingFollow = followRepository.findByFollowerAndFollows(follower, followed);
//	        if (existingFollow != null) {
//	            throw new IllegalStateException("Follow request already sent");
//	        }

		Follow follow = new Follow();
		follow.setFollower(followed);
		follow.setFollowed(follower);
		follow.setDateFollowed(LocalDateTime.now());

		followRepository.save(follow);
	}

}
