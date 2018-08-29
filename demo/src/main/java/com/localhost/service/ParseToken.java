package com.localhost.service;

import com.localhost.model.UserDetailForToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseToken {

	private static final String secret = "${token}";

	public static UserDetailForToken parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			UserDetailForToken user = new UserDetailForToken();
			user.setEmail(body.getSubject());
			user.setRole((String) body.get("role"));
			user.setTimeline((String) body.get("timeline"));
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserDetailForToken user = parseToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWV1Lm5ndXllbi5pbnRlcm5AbnRxLXNvbHV0aW9uLmNvbS52biIsInJvbGUiOiJjYXB0YWluIiwidGltZWxpbmUiOiIxNTM0MzA1ODIyOTUzIn0.hkDnPffwFWzt1qBHvllJs3-D-WdhgRwjcY8L0cSyjQsWRXQqrqvRbPJHZZwyy6XjVfNIOKeBnDz7lJFz8hdgOg");
		System.out.println("role= " + user.getRole());
	}
}
