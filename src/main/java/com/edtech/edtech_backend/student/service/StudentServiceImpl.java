package com.edtech.edtech_backend.student.service;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import com.edtech.edtech_backend.common.enums.Role;
import com.edtech.edtech_backend.common.exception.ResourceNotFoundException;
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

        User user = getLoggedInUser();


        System.out.println("==== STUDENT PROFILE DEBUG ====");
        System.out.println("User ID    : " + user.getId());
        System.out.println("User Email : [" + user.getEmail() + "]");
        System.out.println("================================");

        Student student = studentRepository.findByUser_Email(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));

        return StudentProfileResponseDto.from(student);
    }




    @Override
    public StudentProfileResponseDto getStudentById(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return StudentProfileResponseDto.from(student);
    }

    @Override
    public void updateStudentById(Long studentId, UpdateStudentProfileDto dto) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setFullName(dto.getFullName());
        student.setFatherName(dto.getFatherName());
        student.setFatherPhone(dto.getFatherPhone());
        student.setAddress(dto.getAddress());

        studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        User user = student.getUser();

        studentRepository.delete(student);   // delete student first
        userRepository.delete(user);         // then delete user
    }

    @Override
    public void uploadAvatarByStudentId(Long studentId, StudentAvatarUploadDto dto) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setAvatarUrl(dto.getAvatarUrl());
        studentRepository.save(student);
    }


    private User getLoggedInUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof CustomUserDetails userDetails)) {
            throw new ResourceNotFoundException("Invalid authentication");
        }

        return userDetails.getUser();
    }

}
