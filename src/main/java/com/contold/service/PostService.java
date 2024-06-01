package com.contold.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.contold.entity.Post;
import com.contold.entity.User;
import com.contold.repository.PostRepository;
import com.contold.repository.UserRepository;

@Service
public class PostService {

	private final PostRepository postRepository;
	
	private final UserRepository userRepository;


	public PostService(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		
	}

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
	
//	public List<Post> getAllPostDescSort(){
//		return postRepository.findAll(null)
//	}

	public Optional<Post> getPostById(Long id) {
		return postRepository.findById(id);
	}
	
	
	public List<Post> getLast2PostOfCurrentUser(Long id) {
		
		return postRepository.findTop2ByPostFromIdOrderByCreatedAtDesc(id);
	}
	
	
	

	public Post createPost(Post post, Long id) {
//		post.setCommits(new ArrayList<Commit>()); buna gerek kalmadı cunku entitydeki baglı bir liste default olarak null degil [] olarak tutuluyor.
		Optional<User> userId = userRepository.findById(id);
		if(userId.isPresent()) {
			post.setPostFrom(userRepository.findById(id).get());
		}
		
		else {
			throw new NoSuchElementException();
		}
		
		return postRepository.save(post);
	}

	public Optional<Post> updatePost(Long id, Post newPost) {
		Optional<Post> existingPost = postRepository.findById(id);
		if (existingPost.isPresent()) {
			Post postToUpdate = existingPost.get();
			postToUpdate.setArticle(newPost.getArticle());
			postToUpdate.setLikes(newPost.getLikes());

			return Optional.of(postRepository.save(postToUpdate));
		}
		return Optional.empty();
	}

	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	
	


}
