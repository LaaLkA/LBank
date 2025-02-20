package com.laalka.authservice.utils;

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

    public String generateToken(String username, String role) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRATION_TIME_MS);

        Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
