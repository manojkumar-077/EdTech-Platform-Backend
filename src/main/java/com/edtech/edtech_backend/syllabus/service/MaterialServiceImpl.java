package com.edtech.edtech_backend.syllabus.service;

import com.edtech.edtech_backend.common.exception.ResourceNotFoundException;
import com.edtech.edtech_backend.entity.Material;
import com.edtech.edtech_backend.entity.Subject;
import com.edtech.edtech_backend.repository.MaterialRepository;
import com.edtech.edtech_backend.repository.SubjectRepository;
import com.edtech.edtech_backend.syllabus.dto.MaterialResponseDto;
import com.edtech.edtech_backend.syllabus.dto.MaterialUploadDto;
import com.edtech.edtech_backend.syllabus.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public void uploadMaterial(MaterialUploadDto dto) {

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        Material material = Material.builder()
                .subject(subject)
                .originalFileName(dto.getOriginalFileName())
                .storedFileName(dto.getStoredFileName())
                .fileType(dto.getFileType())
                .fileSize(dto.getFileSize())
                .build();   // uploadDate set automatically by @PrePersist

        materialRepository.save(material);
    }

    @Override
    public List<MaterialResponseDto> getMaterials(Long subjectId) {

        return materialRepository.findBySubject_Id(subjectId)
                .stream()
                .map(MaterialResponseDto::from)
                .collect(Collectors.toList());
    }
}
