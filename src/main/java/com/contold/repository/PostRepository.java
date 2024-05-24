package com.contold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contold.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
