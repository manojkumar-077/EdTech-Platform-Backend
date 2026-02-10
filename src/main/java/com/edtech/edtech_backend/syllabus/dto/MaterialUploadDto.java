package com.edtech.edtech_backend.syllabus.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MaterialUploadDto {

    private Long subjectId;
    private String originalFileName;
    private String storedFileName;
    private String fileType;
    private long fileSize;
}
