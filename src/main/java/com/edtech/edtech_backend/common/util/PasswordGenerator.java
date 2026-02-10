package com.edtech.edtech_backend.common.util;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String CHAR_SET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
    private static final int DEFAULT_LENGTH = 10;

    private static final SecureRandom random = new SecureRandom();

    private PasswordGenerator() {
        // utility class
    }

    public static String generate() {
        StringBuilder password = new StringBuilder(DEFAULT_LENGTH);
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            password.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return password.toString();
    }
}
