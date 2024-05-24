package com.contold.mapper;

import java.util.ArrayList;
import java.util.List;

import com.contold.dto.CreateCommitDTO;
import com.contold.dto.DisplayCommitsDTO;
import com.contold.dto.DisplayNewCommitDTO;
import com.contold.entity.Commit;

public class CommitMapper {

	public static DisplayCommitsDTO fromEntityToDisplayDTO(Commit commit) {

		DisplayCommitsDTO dto = new DisplayCommitsDTO();
		dto.setId(commit.getId());
		dto.setArticle(commit.getArticle());
		dto.setLikes(commit.getLikes());
		dto.setFromId(commit.getCommitFrom().getId());
		dto.setPostId(commit.getPost().getId());
		dto.setCreatedAt(commit.getCreatedAt());
		dto.setFromUsername(commit.getCommitFrom().getUsername());
		return dto;

	}
	
	public static DisplayNewCommitDTO fromEntityToDisplayNewDTO(Commit commit) {
		DisplayNewCommitDTO dto = new DisplayNewCommitDTO();
		dto.setId(commit.getId());
		dto.setArticle(commit.getArticle());
		dto.setPostId(commit.getPost().getId());
		dto.setFromId(commit.getCommitFrom().getId());
		dto.setCreatedAt(commit.getCreatedAt());
		dto.setFromUsername(commit.getCommitFrom().getUsername());

		return dto;

	}

	public static List<DisplayCommitsDTO> fromEntityToDisplayDTO(List<Commit> commitList) {
		List<DisplayCommitsDTO> dtoList = new ArrayList<>();

		for (Commit commit : commitList) {

			dtoList.add(fromEntityToDisplayDTO(commit));

		}

		return dtoList;
	}

	public static Commit toEntity(CreateCommitDTO createCommitDTO) {
		
		Commit commit = new Commit();
		
		commit.setArticle(createCommitDTO.getArticle());
		
		return commit;
	}
	
}
