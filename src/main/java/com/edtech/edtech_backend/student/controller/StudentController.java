package com.edtech.edtech_backend.student.controller;

import com.edtech.edtech_backend.student.dto.*;
import com.edtech.edtech_backend.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ADMIN → CREATE STUDENT
    @PostMapping
    public ResponseEntity<String> createStudent(
            @RequestBody CreateStudentDto dto) {
        studentService.createStudent(dto);
        return ResponseEntity.ok("Student created successfully");
    }

    // STUDENT → VIEW PROFILE
    @GetMapping("/profile")
    public ResponseEntity<StudentProfileResponseDto> getProfile() {
        return ResponseEntity.ok(studentService.getProfile());
    }
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentProfileResponseDto> getStudentById(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{studentId}")
    public ResponseEntity<String> updateStudentById(
            @PathVariable Long studentId,
            @RequestBody UpdateStudentProfileDto dto) {

        studentService.updateStudentById(studentId, dto);
        return ResponseEntity.ok("Student updated successfully");
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudentById(
            @PathVariable Long studentId) {

        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }



    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/{studentId}/avatar")
    public ResponseEntity<String> uploadAvatarByStudentId(
            @PathVariable Long studentId,
            @RequestBody StudentAvatarUploadDto dto) {

        studentService.uploadAvatarByStudentId(studentId, dto);
        return ResponseEntity.ok("Avatar updated successfully");
    }

}
