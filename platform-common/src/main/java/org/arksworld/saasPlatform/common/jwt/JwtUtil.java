package org.arksworld.saasPlatform.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;


public class JwtUtil {

    private final static String SECRET = "uulSyNaTWWkTdPWOkrCQjNzqm7wDhvOX3RER5iWn7yE";

    public static String generateToken(String username, String tenantId) {

        return Jwts.builder()
                .setSubject(username)
                .claim("tenantId", tenantId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getTenantId(String token) {
        return extractClaims(token).get("tenantId", String.class);
    }

    public static String getUsername(String token) {
        return extractClaims(token).getSubject();
    }
}