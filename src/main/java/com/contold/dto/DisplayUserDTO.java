package com.contold.dto;

import java.util.List;

import com.contold.entity.Follow;

import lombok.Data;

@Data
public class DisplayUserDTO {

	private Long id;
	private String username;
	private List<DisplayFollowerDTO> followers;
	private List<DisplayFollowingDTO> following;
	private List<DisplayPostDTO> posts;
//	private List<Commit> commits; Maybe this will be added later.
}
