package com.om.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String secretKey = "";

	public JwtService() throws NoSuchAlgorithmException {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = generator.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException(e.getMessage());
		}
	}

	public String generateToken(String email) throws InvalidKeyException, NoSuchAlgorithmException {

		Map<String, Object> claims = new HashMap<>();
		claims.put("email", email);

		String token = Jwts.builder()
				.claims()
				.add(claims)
				.subject(email)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*5))
				.and().signWith(getKey())
				.compact();

		return token;
	}

	
	public Key getKey() throws NoSuchAlgorithmException {
		byte[] byteArr = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(byteArr);
	}
	
	
	public SecretKey decryptKey(String secretKey2) {
		byte[] byteArr = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(byteArr);
	}


	public Claims extractAllClaims(String token) {
		Claims claims = Jwts.parser().verifyWith(decryptKey(secretKey)).build().parseSignedClaims(token).getPayload();
		return claims;
	}
	

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	public Date extractExpirateDate(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	
	public boolean validateToken(String token, UserDetails userDetails) {
		String userName = extractEmail(token);
		boolean isExpired = isTokenExpired(token);
		
		if (userName.equals(userDetails.getUsername()) && !isExpired) {
			return true;
		}
		
		return false;
	}
	
	public boolean isTokenExpired(String token) {
		
		Date expiratoryDate = extractExpirateDate(token);
		return expiratoryDate.before(new Date());		
	}

}
