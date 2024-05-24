package com.contold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contold.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}
