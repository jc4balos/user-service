package com.jc4balos.user_service.utils;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jc4balos.user_service.model.Role;
import com.jc4balos.user_service.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_EXPIRATION}")
    private Long expiration; // in milliseconds

    public String generateToken(User user, List<Role> rolesList) {
        try {
            return Jwts.builder()
                    .subject(user.getUsername()) // was .setSubject()
                    .claim("user_uuid", user.getUserUUID())
                    .claim("roles", rolesList.stream()
                            .map(Role::getRoleName)
                            .toList()) // Convert Role objects to a list of role names
                    .issuedAt(new Date()) // was .setIssuedAt()
                    .notBefore(new Date()) // 👈 ADD THIS
                    .expiration(new Date(System.currentTimeMillis() + expiration)) // was .setExpiration()
                    .signWith(getSigningKey()) // no need to pass algorithm separately
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions related to JWT generation
            throw new RuntimeException("Error generating JWT token", e);
        }

    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}