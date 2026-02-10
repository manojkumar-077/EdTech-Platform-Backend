package com.edtech.edtech_backend.syllabus.controller;

import com.edtech.edtech_backend.syllabus.dto.CreateSubjectDto;
import com.edtech.edtech_backend.syllabus.dto.SubjectResponseDto;
import com.edtech.edtech_backend.syllabus.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/syllabus/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // ADMIN → CREATE SUBJECT
    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(
            @RequestBody CreateSubjectDto dto) {
        return ResponseEntity.ok(subjectService.createSubject(dto));
    }

    // ADMIN / STUDENT → VIEW SUBJECTS BY CLASS
    @GetMapping("/{classGrade}")
    public ResponseEntity<List<SubjectResponseDto>> getSubjects(
            @PathVariable String classGrade) {
        return ResponseEntity.ok(subjectService.getSubjectsByClass(classGrade));
    }
}
