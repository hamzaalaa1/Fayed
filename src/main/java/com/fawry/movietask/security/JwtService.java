package com.fawry.movietask.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {



        private final String secret = "mysecretkey12345678901234567890mysecretkey12345678901234567890mysecretkey12345678901234567890";
        private final long expirationMs = 1000 * 60 * 60;

        private Key getSigningKey() {
            return Keys.hmacShaKeyFor(secret.getBytes());
        }

        public String generateToken(String username, Map<String,Object> claims) {
            return Jwts.builder()
                    .setSubject(username)
                    .addClaims(claims)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        public String extractUsername(String token) {
            return parseToken(token).getBody().getSubject();
        }

        public boolean isTokenValid(String token) {
            try {
                parseToken(token);
                return true;
            } catch (JwtException e) {
                return false;
            }
        }

        private Jws<Claims> parseToken(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
        }
}
