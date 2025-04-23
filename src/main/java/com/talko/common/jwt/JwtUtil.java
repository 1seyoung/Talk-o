package com.talko.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private long expirationTime;

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String createAccessToken(String email, Long userId) {
    Date now = new Date();
    Date expiration = new Date(now.getTime() + expirationTime);

    return Jwts.builder()
        .setSubject(email)
        .claim("userId", userId)
        .setIssuedAt(now)
        .setExpiration(expiration)
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean validateAccessToken(String token) {
    if (token == null || token.isEmpty()) {
      return false;
    }
    try {
      Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(token);

      Claims claims = extractClaims(token);
      Date expiration = claims.getExpiration();
      return !expiration.before(new Date());
    } catch (JwtException e) {
      return false;
    }
  }

  private Claims extractClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String extractEmail(String token) {
    return extractClaims(token).getSubject();
  }

  public Long extractUserId(String token) {
    return extractClaims(token).get("userId", Long.class);
  }

}
