package com.edtech.edtech_backend.student.dto;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentDto {

    private String fullName;
    private String email;
    private ClassGrade classGrade;
    private String schoolName;
}
