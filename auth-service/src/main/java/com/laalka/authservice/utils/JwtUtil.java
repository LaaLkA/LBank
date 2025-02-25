package com.laalka.authservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "805cdbf68d77b32222b2bcea6771fd6ee152e3388413fc63ecf4323377010a30";

    private static final long EXPIRATION_TIME_MS = 24 * 60 * 60 * 1000;

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Генерируем JWT на основе username, роли и т.д.
     */
    public String generateToken(String username, String role) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRATION_TIME_MS);


        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Парсим токен и возвращаем Claims.
     * Если токен невалиден или просрочен —
     * то выбросится соответствующее исключение.
     */
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Извлечение userName из token
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
