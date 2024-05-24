package com.contold.dto;

import lombok.Data;

@Data
public class CreateCommitDTO {
	
	
	String article;
	Long postId;
	Long fromId;

}
