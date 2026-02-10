package com.edtech.edtech_backend.quiz.dto;

import com.edtech.edtech_backend.entity.Quiz;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizResponseDto {

    private Long id;
    private String subjectName;
    private String classGrade;
    private int durationMinutes;

    public static QuizResponseDto from(Quiz quiz) {
        return QuizResponseDto.builder()
                .id(quiz.getId())
                .subjectName(quiz.getSubjectName())
                .classGrade(quiz.getClassGrade().name())
                .durationMinutes(quiz.getDurationMinutes())
                .build();
    }
}
