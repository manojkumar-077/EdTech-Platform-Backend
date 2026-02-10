package com.edtech.edtech_backend.quiz.dto;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizDto {

    private ClassGrade classGrade;
    private String subjectName;
    private int durationMinutes;
}

