package com.invoiceapp.invoice_API.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Component
public class JwtUtil {

    private final Key key;

    @Value("${jwt.accessTokenExpirationMs}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refreshTokenExpirationMs}")
    private long refreshTokenExpirationMs;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] decodedKey = Base64.getEncoder().encode(secret.getBytes());
        this.key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());
    }

    // 🔐 Generate Access Token (Short-lived)
    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpirationMs);
    }

    // 🔐 Generate Refresh Token (Long-lived)
    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenExpirationMs);
    }

    // ⚙️ Token Generator
    private String generateToken(String username, long expirationMillis) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    // ✅ Validate Token & Get Email
    public String validateTokenAndGetEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Optional: Check Token Expiry (Useful for debugging)
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
