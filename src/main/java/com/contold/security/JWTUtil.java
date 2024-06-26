package com.contold.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.contold.security.JWTConstants.SECRET_KEY;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;

@Component
public class JWTUtil {
	
	@Value("${jwt.accessToken.expirationMs}")
	private Long accessTokenExpirationMs;

	public String generateToken(UserDetails userDetails) {
		String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(", "));
		System.out.println(roles);
		return doGenerateToken(roles, userDetails.getUsername(), accessTokenExpirationMs);
	}

	private String doGenerateToken(String roles, String subject, Long expirationMs) {
		try {
			return JWT.create().withSubject(subject).withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
					.withClaim("roleName", roles).sign(HMAC512(SECRET_KEY));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
