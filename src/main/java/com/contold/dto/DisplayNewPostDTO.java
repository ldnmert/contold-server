package com.contold.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DisplayNewPostDTO {

	private Long id;
	private String article;
	private LocalDateTime createdAt;
	private String fromUsername;
	private Long fromId;
	

}
