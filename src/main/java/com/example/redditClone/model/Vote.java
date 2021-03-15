package com.example.redditClone.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long vodeId;
	private VoteType voteType;
	
	@NotNull
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "postId",referencedColumnName = "postId")
	private Post post;
	
	@NotNull
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	private User user;
}
