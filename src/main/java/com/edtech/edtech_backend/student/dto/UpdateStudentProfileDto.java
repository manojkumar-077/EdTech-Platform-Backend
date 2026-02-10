package com.edtech.edtech_backend.student.dto;

import lombok.Data;

@Data
public class UpdateStudentProfileDto {

    private String fullName;
    private String fatherName;
    private String fatherPhone;
    private String address;
}
