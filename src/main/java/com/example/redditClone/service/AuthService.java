package com.example.redditClone.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.redditClone.dto.RegisterRequest;
import com.example.redditClone.model.NotificationEmail;
import com.example.redditClone.model.User;
import com.example.redditClone.model.VerificationToken;
import com.example.redditClone.repository.UserRepository;
import com.example.redditClone.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;

	@Transactional
	public void signup(RegisterRequest registerRequest) {
		registerRequest.tostr();
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
				"Thank you for signing up to Spring Reddit, "
						+ "please click on the below url to activate your account : "
						+ "http://localhost:8080/api/auth/accountVerification/" + token));
	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		
		verificationTokenRepository.save(verificationToken);
		return token;
	}
}