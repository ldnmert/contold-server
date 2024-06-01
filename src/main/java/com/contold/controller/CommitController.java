package com.contold.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contold.dto.CreateCommitDTO;
import com.contold.dto.DisplayCommitsDTO;
import com.contold.dto.DisplayNewCommitDTO;
import com.contold.entity.Commit;
import com.contold.mapper.CommitMapper;
import com.contold.repository.FollowRepository;
import com.contold.service.CommitService;
import com.contold.service.PostService;
import com.contold.service.UserService;

@RestController
@RequestMapping("/commit")
@CrossOrigin("*")
public class CommitController {

	private final CommitService commitService;
	private final PostService postService;
	private final UserService userService;
	private final FollowRepository followRepository;

	public CommitController(CommitService commitService, PostService postService, UserService userService,
			FollowRepository followRepository) {
		this.commitService = commitService;
		this.postService = postService;
		this.userService = userService;
		this.followRepository = followRepository;
	}

	@PostMapping
	public ResponseEntity<DisplayNewCommitDTO> createCommit(@RequestBody CreateCommitDTO createCommitDTO,
			Authentication auth) {

		Long idOfCurrentUser = userService.findByUsername(auth.getName()).getId();

		Long idOfUserPosted = postService.getPostById(createCommitDTO.getPostId()).get().getPostFrom().getId();

		Long checkFollow = followRepository.existsByFollowerIdAndFollowedId(idOfCurrentUser, idOfUserPosted);

		boolean checkUser = idOfCurrentUser == createCommitDTO.getFromId();

		if (checkUser && checkFollow == 1) {

			Commit commit = CommitMapper.toEntity(createCommitDTO);

			Commit createdCommit = commitService.createCommit(commit, createCommitDTO.getFromId(),
					createCommitDTO.getPostId());

			DisplayNewCommitDTO dto = CommitMapper.fromEntityToDisplayNewDTO(createdCommit);
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}

		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCommit(@PathVariable Long id, Authentication auth) {
		if (commitService.getCommitById(id).get().getCommitFrom().getId() == userService.findByUsername(auth.getName())
				.getId()) {
			commitService.deleteCommit(id);
			return ResponseEntity.noContent().build();
		}

		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

	}

//	@PutMapping("/{id}")
//	public ResponseEntity<DisplayCommitsDTO> updateCommit(@PathVariable Long id, @RequestBody Commit commit) {
//		Optional<Commit> updatedCommit = commitService.updateCommit(id, commit);
//		if (updatedCommit.isPresent()) {
//			DisplayCommitsDTO dto = CommitMapper.toDTO(updatedCommit.get());
//			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

//	@GetMapping
//	public ResponseEntity<List<DisplayCommitsDTO>> getAllCommits() {
//		List<Commit> commits = commitService.getAllCommits();
//		List<DisplayCommitsDTO> listofCommitsDTO = CommitMapper.fromEntityToDisplayDTO(commits);
//
//		return ResponseEntity.ok(listofCommitsDTO);
//	}

//	@GetMapping("/{id}")
//	public ResponseEntity<DisplayCommitsDTO> getCommitById(@PathVariable Long id) {
//		Optional<Commit> commit = commitService.getCommitById(id);
//		
//		if (commit.isPresent()) {
//			DisplayCommitsDTO dto = CommitMapper.fromEntityToDisplayDTO(commit.get());
//			return new ResponseEntity<>(dto, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

}
