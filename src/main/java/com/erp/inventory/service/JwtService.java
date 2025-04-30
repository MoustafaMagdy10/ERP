package com.erp.inventory.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


    private final String SECRET;

    public JwtService() {
        try{
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = generator.generateKey();
            SECRET = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String username) {

        Map<String, Object> customClaims = new HashMap<>();

        // Build and return the JWT
        return Jwts.builder()
                .claims() // Start defining claims
                .add(customClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 * 30))
                .and() // Finalize claims
                .signWith(getKey()) // Sign the JWT with a key
                .compact(); // Compact the JWT into a string
    }

    private SecretKey getKey() {
            byte[] keyBytes = Base64.getDecoder().decode(SECRET);
            return Keys.hmacShaKeyFor(keyBytes);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
       public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}