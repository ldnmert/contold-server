package com.contold.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contold.dto.CreatePostDTO;
import com.contold.dto.DisplayNewPostDTO;
import com.contold.dto.DisplayPostDTO;
import com.contold.entity.Post;
import com.contold.mapper.PostMapper;
import com.contold.service.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public ResponseEntity<List<DisplayPostDTO>> getAllPosts() {
		List<Post> posts = postService.getAllPosts();
		
		List<DisplayPostDTO> postsDisplay = PostMapper.fromEntityToDisplayDTO(posts);
		
		

		return ResponseEntity.ok(postsDisplay);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DisplayPostDTO> getPostById(@PathVariable Long id) {
		Optional<Post> post = postService.getPostById(id);
		if (post.isPresent()) {

			DisplayPostDTO displayPostDto = PostMapper.fromEntityToDisplayDto(post.get());
			return ResponseEntity.ok(displayPostDto);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping
	public ResponseEntity<DisplayNewPostDTO> createPost(@RequestBody CreatePostDTO createPostDTO) {

		Post post = PostMapper.toEntity(createPostDTO);
		Post createdPost = postService.createPost(post, createPostDTO.getFromId());

		DisplayNewPostDTO dto = PostMapper.fromEntityToNewDisplayDTO(createdPost);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<DisplayPostDTO> updatePost(@PathVariable Long id, @RequestBody Post post) {
//		Optional<Post> updatedPost = postService.updatePost(id, post);
//		if (updatedPost.isPresent()) {
//			DisplayPostDTO dto = PostMapper.toDTO(updatedPost.get());
//			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.noContent().build();
	}

}
