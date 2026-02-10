package com.edtech.edtech_backend.student.service;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import com.edtech.edtech_backend.common.enums.Role;
import com.edtech.edtech_backend.common.util.PasswordGenerator;
import com.edtech.edtech_backend.entity.Student;
import com.edtech.edtech_backend.entity.User;
import com.edtech.edtech_backend.repository.StudentRepository;
import com.edtech.edtech_backend.repository.UserRepository;
import com.edtech.edtech_backend.security.CustomUserDetails;
import com.edtech.edtech_backend.student.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN → CREATE STUDENT
    @Override
    public void createStudent(CreateStudentDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Student email already exists");
        }

        String rawPassword = PasswordGenerator.generate();

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(rawPassword))
                .role(Role.STUDENT)
                .isActive(true)
                .build();

        userRepository.save(user);

        Student student = Student.builder()
                .user(user)
                .fullName(dto.getFullName())
                .classGrade(dto.getClassGrade())
                .schoolName(dto.getSchoolName())
                .build();

        studentRepository.save(student);

        // NOTE:
        // Email sending will be wired later using MailConfig
        // rawPassword is used there
    }

    // STUDENT → VIEW PROFILE
    @Override
    public StudentProfileResponseDto getProfile() {

        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        User user = userDetails.getUser();

        Student student = studentRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return StudentProfileResponseDto.from(student);
    }

    // STUDENT → UPDATE PROFILE (LIMITED FIELDS)
    @Override
    public void updateProfile(UpdateStudentProfileDto dto) {

        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        Student student = studentRepository.findByUserId(userDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        student.setFullName(dto.getFullName());
        student.setFatherName(dto.getFatherName());
        student.setFatherPhone(dto.getFatherPhone());
        student.setAddress(dto.getAddress());

        studentRepository.save(student);
    }

    // STUDENT → AVATAR UPLOAD (METADATA)
    @Override
    public void uploadAvatar(StudentAvatarUploadDto dto) {

        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        Student student = studentRepository.findByUserId(userDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        student.setAvatarUrl(dto.getAvatarUrl());
        studentRepository.save(student);
    }
}
