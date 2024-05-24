package com.contold.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.contold.entity.Commit;

import lombok.Data;

@Data
public class DisplayPostDTO {

	private Long id;
	private String article;
	private Integer likes;
	private Long fromId;
	private String fromUsername;
	private LocalDateTime createdAt;
	private List<DisplayCommitsDTO> listOfCommits;


}
