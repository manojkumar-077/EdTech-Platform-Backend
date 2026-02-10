package com.edtech.edtech_backend.config;

public class JwtConfig {

    public static final String SECRET_KEY = "EDTECH_SECRET_KEY_CHANGE_LATER";
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private JwtConfig() {
        // utility class
    }
}
