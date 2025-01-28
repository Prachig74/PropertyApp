package com.example.PropertyApp.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
//public class JwtUtil {
//
//    private String secretKey = "pp";
//    // Ideally should be more secure
//
//    // Generate Token
//    public String generateToken(String username) {
//        return JWT.create()
//                .withSubject(username)
//                .withIssuedAt(new Date())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
//                .sign(Algorithm.HMAC256(secretKey));
//    }
//
//    // Extract Username from Token
//    public String extractUsername(String token) {
//        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
//                .build()
//                .verify(token);
//        return decodedJWT.getSubject();
//    }
//
//
//
//
//    public String validateToken(String token) {
//        try {
//            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
//                    .build()
//                    .verify(token);
//            return decodedJWT.getSubject(); // Return user information (subject)
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid Token");
//        }
//    }
//}




public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey; // Load from application.properties or environment variable

    @Value("${jwt.expiration}")
    private long expirationTime; // Token expiration time in milliseconds

    // Generate Token
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime)) // Configurable expiration
                .sign(Algorithm.HMAC256(secretKey));
    }

    // Extract Username from Token
    public String extractUsername(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);
            return decodedJWT.getSubject(); // Extract the "subject" (username)
        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException("Invalid token: " + e.getMessage());
        }
    }

    // Validate Token
    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token); // Will throw an exception if invalid
            return true;
        } catch (JWTVerificationException e) {
            return false; // Invalid token
        }
    }
}
