package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    /**
     * Test generating a large number of UUIDs.
     */
    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    /**
     * Test generating a JWT with custom claims.
     */
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima") // Sign using HS256 algorithm with a key
                .setClaims(claims) // Set custom claims
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // Set expiration time to 1 hour
                .compact();
        System.out.println(jwt);
    }

    /**
     * Test parsing a JWT and retrieving its claims.
     */
    @Test
    public void testParseJwt() {
        // This is a hard-coded JWT token for testing purposes.
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY3MDQ2NDU0N30.yPLRyiusrlrmWeC4-dhInjFuAghPkmiHSRHd_DTKi9E";
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
