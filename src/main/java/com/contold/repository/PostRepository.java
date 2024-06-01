package com.contold.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contold.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findTop2ByPostFromIdOrderByCreatedAtDesc(Long userId);
	
}
