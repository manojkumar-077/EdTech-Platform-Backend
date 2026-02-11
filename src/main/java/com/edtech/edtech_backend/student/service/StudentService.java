package com.edtech.edtech_backend.student.service;

import com.edtech.edtech_backend.student.dto.*;

public interface StudentService {

    void createStudent(CreateStudentDto dto);

    StudentProfileResponseDto getProfile();
    StudentProfileResponseDto getStudentById(Long studentId);
    void updateStudentById(Long studentId, UpdateStudentProfileDto dto);

    void deleteStudentById(Long studentId);
    void uploadAvatarByStudentId(Long studentId, StudentAvatarUploadDto dto);




}
