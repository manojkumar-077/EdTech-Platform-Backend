package com.edtech.edtech_backend.common.util;

import java.util.UUID;

public class FileUtil {

    private FileUtil() {
        // utility class
    }

    // Generate unique stored filename to avoid collisions
    public static String generateStoredFileName(String originalFileName) {
        String extension = "";

        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = originalFileName.substring(dotIndex);
        }

        return UUID.randomUUID() + extension;
    }
}
