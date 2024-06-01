package com.contold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contold.entity.Commit;

public interface CommitRepository extends JpaRepository<Commit, Long> {


	
}
