package com.contold.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.contold.dto.DisplayFollowingDTO;
import com.contold.dto.DisplayPostDTO;
import com.contold.entity.Post;
import com.contold.entity.User;
import com.contold.mapper.FollowMapper;
import com.contold.mapper.PostMapper;

@Service
public class MainPageService {

	private final UserService userService;
	private final PostService postService;

	public MainPageService(UserService userService, PostService postService) {
		this.postService = postService;
		this.userService = userService;
	}

	public List<DisplayPostDTO> getMainPageOfCurrentUser(Authentication auth) {

		User currentUser = userService.findByUsername(auth.getName());

		List<DisplayFollowingDTO> followingList = FollowMapper
				.fromEntityToDisplayFollowingDTO(currentUser.getFollowing());
		List<Long> idsOfFollowings = new ArrayList<Long>();
		List<DisplayPostDTO> mainPagePosts = new ArrayList<DisplayPostDTO>();

		for (DisplayFollowingDTO following : followingList) {

			idsOfFollowings.add(following.getId());
		}

		for (Long id : idsOfFollowings) {
			List<DisplayPostDTO> twoPostOfFollowingUser = PostMapper
					.fromEntityToDisplayDTO((postService.getLast2PostOfCurrentUser(id)));
			mainPagePosts.addAll(twoPostOfFollowingUser);

		}

		return mainPagePosts;
	}

}
