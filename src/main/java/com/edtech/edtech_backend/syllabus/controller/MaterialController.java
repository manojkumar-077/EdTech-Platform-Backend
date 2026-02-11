package com.edtech.edtech_backend.syllabus.controller;

import com.edtech.edtech_backend.syllabus.dto.MaterialResponseDto;
import com.edtech.edtech_backend.syllabus.dto.MaterialUploadDto;
import com.edtech.edtech_backend.syllabus.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/syllabus/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    // ADMIN → UPLOAD MATERIAL
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<String> uploadMaterial(@RequestBody MaterialUploadDto dto) {
        materialService.uploadMaterial(dto);
        return ResponseEntity.ok("Material uploaded successfully");
    }

    // STUDENT → VIEW MATERIALS BY SUBJECT
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<MaterialResponseDto>> getMaterials(
            @PathVariable Long subjectId) {
        return ResponseEntity.ok(materialService.getMaterials(subjectId));
    }
}
