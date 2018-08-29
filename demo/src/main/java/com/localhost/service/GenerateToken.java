package com.localhost.service;

import com.localhost.model.UserDetailForToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class GenerateToken {

	public static final String secret = "${token}";

	public static String generateToken(UserDetailForToken user) {
		Claims claims = Jwts.claims().setSubject(user.getEmail());
		claims.put("role", user.getRole() + "");
		claims.put("timeline", user.getTimeline());

		return Jwts.builder().setClaims(claims).signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret).compact();
	}
}
