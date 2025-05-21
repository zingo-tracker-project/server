package com.tracker.server.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.access-token-expiration}")
    private int ACCESS_TOKEN_EXPIRATION; // milliseconds (ex: 3600000 = 1시간)

    @Value("${jwt.refresh-token-expiration}")
    private int REFRESH_TOKEN_EXPIRATION; // milliseconds (ex: 1209600000 = 14일)

    // Access Token 생성
    public String generateAccessToken(String userId) {
        return generateToken(userId, ACCESS_TOKEN_EXPIRATION);
    }

    public String generateRefreshToken(String userId) {
        return generateToken(userId, REFRESH_TOKEN_EXPIRATION);
    }

    public JwtResDTO generateJwtToken(String userId) {
        String accessToken = generateAccessToken(userId);
        String refreshToken = generateRefreshToken(userId);
        return JwtResDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims.get("userId", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = parseClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (ExpiredJwtException e) {
            log.debug("토큰 만료됨");
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("토큰 유효성 오류: " + e.getMessage());
            return false;
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 내부용: 토큰 생성 공통 로직
    private String generateToken(String userId, int expirationTime) {
        return Jwts.builder()
                .setSubject("userInfo")
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // 내부용: Claims 파싱
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // 여기 수정됨
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
