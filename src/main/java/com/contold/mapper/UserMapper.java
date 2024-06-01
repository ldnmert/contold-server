package com.contold.mapper;

import com.contold.dto.CreateUserDTO;
import com.contold.dto.DisplayUserDTO;
import com.contold.entity.User;

public class UserMapper {

	public static DisplayUserDTO toDTO(User user) {

		DisplayUserDTO displayUserDTO = new DisplayUserDTO();
		displayUserDTO.setId(user.getId());
		displayUserDTO.setUsername(user.getUsername());
		displayUserDTO.setFollowing(FollowMapper.fromEntityToDisplayFollowingDTO(user.getFollowing()));
		displayUserDTO.setFollowers(FollowMapper.fromEntityToDisplaayFollowerDTO(user.getFollowers()));
		displayUserDTO.setPosts(PostMapper.fromEntityToDisplayDTO(user.getPosts()));
		
		return displayUserDTO;
	}
	
	public static User toEntity(CreateUserDTO createUserDTO) {

		User user = new User();

		user.setPassword(createUserDTO.getPassword());
		user.setUsername(createUserDTO.getUsername());
		user.setRole(createUserDTO.getRole());
		
		return user;
	}
	
	

}
