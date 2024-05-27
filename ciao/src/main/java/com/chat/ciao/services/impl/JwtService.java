package com.chat.ciao.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

  private static final String SECRET_KEY = "23VKN234N4N5B34B1KLQPDASCB44231BXX2CBV9063LK5";

  public String getToken(UserDetails user){
    return getToken(new HashMap<>(), user);
  }

  private String getToken(Map<String, Object> extractClaims, UserDetails user){
    SecretKey key = getKey();
    return Jwts
      .builder()
      .claims(extractClaims)
      .subject(user.getUsername())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
      .signWith(key)
      .compact();
  }

  private SecretKey getKey(){
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
  }

  public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Claims getAllClaims(String token){
    return Jwts.parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date getExpiration(String token){
    return getClaim(token, Claims::getExpiration);
  }

  private boolean isTokenExpired(String token){
    return getExpiration(token).before(new Date());
  }
}
