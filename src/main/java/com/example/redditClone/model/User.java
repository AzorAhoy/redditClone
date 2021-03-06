package com.example.redditClone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long userId;

	@NotBlank(message = "Username is required.")
	private String username;

	@NotBlank(message = "Password is required.")
	private String password;
	
	@Email
	@NotBlank(message = "Email is required.")
	private String email;
	
	private Instant created;
	private boolean enabled;
}
