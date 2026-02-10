package com.edtech.edtech_backend.syllabus.service;

import com.edtech.edtech_backend.syllabus.dto.MaterialResponseDto;
import com.edtech.edtech_backend.syllabus.dto.MaterialUploadDto;

import java.util.List;

public interface MaterialService {

    void uploadMaterial(MaterialUploadDto dto);

    List<MaterialResponseDto> getMaterials(Long subjectId);
}
