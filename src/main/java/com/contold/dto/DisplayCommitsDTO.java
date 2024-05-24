package com.contold.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DisplayCommitsDTO {

	private Long id;
	
	private String article;

	private Integer likes;
	
	private Long postId;
	
	private Long fromId;
	private String fromUsername;
	private LocalDateTime createdAt;

}
