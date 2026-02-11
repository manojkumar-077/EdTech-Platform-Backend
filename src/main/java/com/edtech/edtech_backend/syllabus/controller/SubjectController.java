package com.edtech.edtech_backend.syllabus.controller;

import com.edtech.edtech_backend.syllabus.dto.CreateSubjectDto;
import com.edtech.edtech_backend.syllabus.dto.SubjectResponseDto;
import com.edtech.edtech_backend.syllabus.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/syllabus/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // ADMIN → CREATE SUBJECT
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(@RequestBody CreateSubjectDto dto) {
        return ResponseEntity.ok(subjectService.createSubject(dto));
    }

    // ADMIN / STUDENT → VIEW SUBJECTS BY CLASS
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN','SUPER_ADMIN')")
    @GetMapping("/{classGrade}")
    public ResponseEntity<List<SubjectResponseDto>> getSubjects(@PathVariable String classGrade) {
        return ResponseEntity.ok(subjectService.getSubjectsByClass(classGrade));
    }
}
