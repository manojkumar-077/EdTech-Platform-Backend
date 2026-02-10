package com.edtech.edtech_backend.student.controller;

import com.edtech.edtech_backend.student.dto.*;
import com.edtech.edtech_backend.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // STUDENT → UPDATE PROFILE
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(
            @RequestBody UpdateStudentProfileDto dto) {
        studentService.updateProfile(dto);
        return ResponseEntity.ok("Profile updated successfully");
    }

    // STUDENT → UPLOAD AVATAR (metadata only)
    @PostMapping("/profile/avatar")
    public ResponseEntity<String> uploadAvatar(
            @RequestBody StudentAvatarUploadDto dto) {
        studentService.uploadAvatar(dto);
        return ResponseEntity.ok("Avatar updated successfully");
    }
}
