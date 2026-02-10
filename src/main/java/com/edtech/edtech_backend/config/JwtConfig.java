package com.edtech.edtech_backend.config;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JwtConfig {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1 day

    private static final String SECRET_KEY =
            "edtech_backend_secret_key_which_must_be_long_enough";

    public static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
