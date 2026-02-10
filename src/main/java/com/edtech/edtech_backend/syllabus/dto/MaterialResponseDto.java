package com.edtech.edtech_backend.syllabus.dto;

import com.edtech.edtech_backend.entity.Material;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class MaterialResponseDto {

    private Long id;
    private String originalFileName;
    private String storedFileName;
    private String fileType;
    private long fileSize;
    private String uploadDate;

    public static MaterialResponseDto from(Material material) {
        return MaterialResponseDto.builder()
                .id(material.getId())
                .originalFileName(material.getOriginalFileName())
                .storedFileName(material.getStoredFileName())
                .fileType(material.getFileType())
                .fileSize(material.getFileSize())
                .uploadDate(material.getUploadDate().toString())
                .build();
    }
}

