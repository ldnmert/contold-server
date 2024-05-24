package com.contold.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreatePostDTO {


	private String article;
	private Long fromId;


}
