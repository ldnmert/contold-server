package com.contold.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contold.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM follow WHERE follower_id = :followerId AND followed_id = :followedId)", nativeQuery = true)
	Long existsByFollowerIdAndFollowedId(Long followerId, Long followedId);
	
}
