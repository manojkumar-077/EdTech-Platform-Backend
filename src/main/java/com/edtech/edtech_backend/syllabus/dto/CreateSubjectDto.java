package com.edtech.edtech_backend.syllabus.dto;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubjectDto {

    private String name;
    private ClassGrade classGrade;
}
