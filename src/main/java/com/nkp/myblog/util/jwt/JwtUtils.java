package com.nkp.myblog.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nkp.myblog.domain.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class JwtUtils {

    private static final Algorithm ALGORITHM = Algorithm.HMAC512("NEOSK");
    private static final long AUTH_TIME = 60 * 60 ^ 6000;
    private static final long REFRESH_TIME = 60 * 60 * 24 * 7;

    public static String makeAuthToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(AUTH_TIME))
                .withJWTId(UUID.randomUUID().toString())
                .sign(ALGORITHM);
    }

    public static String makeRefreshToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(REFRESH_TIME))
                .sign(ALGORITHM);
    }

    public static VerifyResult verify(String token) {
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return VerifyResult.builder()
                    .success(true)
                    .username(verify.getSubject())
                    .build();
        } catch (JWTDecodeException | TokenExpiredException e) {
            return VerifyResult.builder()
                    .success(false)
                    .build();
        }
    }

}
