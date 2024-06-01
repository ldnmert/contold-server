package com.contold.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;
	
	private String role;
	
	private Integer followerCount;

	private Integer followingCount;

	@OneToMany(mappedBy = "follower")
	private List<Follow> followers;

	@OneToMany(mappedBy = "followed")
	private List<Follow> following;

	@OneToMany(mappedBy = "postFrom", fetch = FetchType.EAGER)
	private List<Post> posts;

	@OneToMany(mappedBy = "commitFrom")
	private List<Commit> commits;

}
