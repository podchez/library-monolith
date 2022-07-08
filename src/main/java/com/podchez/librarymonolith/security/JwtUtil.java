package com.podchez.librarymonolith.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.podchez.librarymonolith.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String GenerateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("library-monolith")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public Map<String, Claim> validateTokenAndRetrieveClaim(String jwtToken) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require((Algorithm.HMAC256(secret)))
                .withSubject("User details")
                .withIssuer("library-monolith")
                .build();

        DecodedJWT decodedJwtToken = verifier.verify(jwtToken);
        return decodedJwtToken.getClaims();
    }
}
