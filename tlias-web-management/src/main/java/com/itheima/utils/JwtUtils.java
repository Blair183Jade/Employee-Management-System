package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * Utility class for handling JSON Web Tokens (JWT).
 * Provides functionality to generate and parse JWTs using a secret key.
 */
public class JwtUtils {

    // Secret key used for signing the JWTs
    private static final String signKey = "itheima";
    // Token validity time in milliseconds (12 hours)
    private static final Long expire = 43200000L;

    /**
     * Generates a JWT with specified claims and a fixed expiration time.
     *
     * @param claims A map of key-value pairs that will be included in the JWT payload.
     * @return A string representation of the JWT.
     */
    public static String generateJwt(Map<String, Object> claims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expire);
        return Jwts.builder()
                .setClaims(claims)  // Add payload data
                .signWith(SignatureAlgorithm.HS256, signKey)  // Sign the token with HS256 algorithm and key
                .setExpiration(expiryDate)  // Set expiration date
                .compact();  // Serialize to a compact, URL-safe string
    }

    /**
     * Parses a JWT and extracts its claims if the token is valid.
     *
     * @param jwt The JWT to parse.
     * @return Claims object containing the token's payload.
     * @throws io.jsonwebtoken.JwtException if any issue is encountered while parsing the token,
     *                                      including expired JWTs, incorrect signature, etc.
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(signKey)  // Set the signing key for verification
                .parseClaimsJws(jwt)  // Parse the compact serialized JWT
                .getBody();  // Retrieve the payload part of the token
    }
}
