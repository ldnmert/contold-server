package com.contold.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DisplayNewCommitDTO {

	Long id;
	String article;
	Long postId;
	Long fromId;
	String fromUsername;
	LocalDateTime createdAt;
	
}
