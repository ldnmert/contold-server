package com.contold.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.contold.dto.DisplayCommitsDTO;
import com.contold.entity.Commit;
import com.contold.entity.Post;
import com.contold.mapper.CommitMapper;
import com.contold.repository.CommitRepository;

@Service
public class CommitService {

	   private final CommitRepository commitRepository;
	    private final PostService postService;
	    private final UserService userService;

	    public CommitService(CommitRepository commitRepository, PostService postService, UserService userService) {
	        this.commitRepository = commitRepository;
	        this.postService = postService;
			this.userService = userService;
	    }
    public List<Commit> getAllCommits() {
        return commitRepository.findAll();
    }

    public Optional<Commit> getCommitById(Long id) {
        return commitRepository.findById(id);
    }
    



	public Commit createCommit(Commit commit, Long fromId, Long postId) {
		commit.setPost(postService.getPostById(postId).get());
		commit.setCommitFrom(userService.findById(fromId));
		Commit savedCommit = commitRepository.save(commit);

		return savedCommit;

	}

    public Optional<Commit> updateCommit(Long id, Commit newCommit) {
        Optional<Commit> existingCommit = commitRepository.findById(id);
        if (existingCommit.isPresent()) {
            Commit commitToUpdate = existingCommit.get();
            commitToUpdate.setArticle(newCommit.getArticle());
            commitToUpdate.setLikes(newCommit.getLikes());

            return Optional.of(commitRepository.save(commitToUpdate));
        }
        return Optional.empty();
    }

    public void deleteCommit(Long id) {
        commitRepository.deleteById(id);
    }
}
