package com.buka.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtils {
	public static String encrypt(Map map, Date  dateout) {
		Date date=new Date();
		JwtBuilder builder= Jwts.builder().setId(UUID.randomUUID().toString())
				.setSubject("JWT")
				.setIssuedAt(date)
				.setExpiration(dateout)
				.addClaims(map)
		.signWith(SignatureAlgorithm.HS256, "itbuka");
		return builder.compact();
	}
	public static Map decrypt(String compactJwt) {
		Claims claims = Jwts.parser().setSigningKey("itbuka").parseClaimsJws(compactJwt).getBody();
		return claims;
	}
}
