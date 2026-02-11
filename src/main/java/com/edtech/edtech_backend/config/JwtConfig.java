package com.edtech.edtech_backend.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtConfig {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 day

    // ✅ SECURE 512-bit key for HS512
    private static final SecretKey SIGNING_KEY =
            Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static SecretKey getSigningKey() {
        return SIGNING_KEY;
    }
}
