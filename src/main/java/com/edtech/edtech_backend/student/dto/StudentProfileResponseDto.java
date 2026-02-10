package com.edtech.edtech_backend.student.dto;

import com.edtech.edtech_backend.entity.Student;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentProfileResponseDto {

    private String fullName;
    private String email;
    private String classGrade;
    private String schoolName;
    private String fatherName;
    private String fatherPhone;
    private String address;
    private String avatarUrl;

    public static StudentProfileResponseDto from(Student student) {
        return StudentProfileResponseDto.builder()
                .fullName(student.getFullName())
                .email(student.getUser().getEmail())
                .classGrade(student.getClassGrade().name())
                .schoolName(student.getSchoolName())
                .fatherName(student.getFatherName())
                .fatherPhone(student.getFatherPhone())
                .address(student.getAddress())
                .avatarUrl(student.getAvatarUrl())
                .build();
    }
}
