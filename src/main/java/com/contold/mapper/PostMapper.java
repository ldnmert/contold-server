package com.contold.mapper;

import java.util.ArrayList;
import java.util.List;

import com.contold.dto.CreatePostDTO;
import com.contold.dto.DisplayNewPostDTO;
import com.contold.dto.DisplayPostDTO;
import com.contold.entity.Post;


public class PostMapper {

	public static DisplayNewPostDTO fromEntityToNewDisplayDTO(Post post) {

		DisplayNewPostDTO displayNewPostDTO = new DisplayNewPostDTO();
		displayNewPostDTO.setArticle(post.getArticle());
		displayNewPostDTO.setCreatedAt(post.getCreatedAt());
		displayNewPostDTO.setId(post.getId());
		displayNewPostDTO.setFromId(post.getPostFrom().getId());
		displayNewPostDTO.setFromUsername(post.getPostFrom().getUsername());

		return displayNewPostDTO;

	}

	public static DisplayPostDTO fromEntityToDisplayDto(Post post) {

		DisplayPostDTO displayPostDTO = new DisplayPostDTO();
		displayPostDTO.setId(post.getId());
		displayPostDTO.setArticle(post.getArticle());
		displayPostDTO.setLikes(post.getLikes());
		displayPostDTO.setCreatedAt(post.getCreatedAt());
		displayPostDTO.setFromId(post.getPostFrom().getId());
		displayPostDTO.setListOfCommits(CommitMapper.fromEntityToDisplayDTO(post.getCommits()));
		displayPostDTO.setFromUsername(post.getPostFrom().getUsername());
		return displayPostDTO;

	}

	public static List<DisplayPostDTO> fromEntityToDisplayDTO(List<Post> postList) {
		List<DisplayPostDTO> dtoList = new ArrayList<>();

		for (Post post : postList) {
			DisplayPostDTO dto = fromEntityToDisplayDto(post);

			dtoList.add(dto);
		}

		return dtoList;
	}

	public static Post toEntity(CreatePostDTO createPostDTO) {

		Post post = new Post();

//		User user = new User();
//		user.setId(createPostDTO.getFromId());

		
		post.setArticle(createPostDTO.getArticle());
//		post.setFrom(user);

		return post;

	}

}
