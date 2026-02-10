package com.edtech.edtech_backend.student.service;

import com.edtech.edtech_backend.student.dto.*;

public interface StudentService {

    void createStudent(CreateStudentDto dto);

    StudentProfileResponseDto getProfile();

    void updateProfile(UpdateStudentProfileDto dto);

    void uploadAvatar(StudentAvatarUploadDto dto);
}
