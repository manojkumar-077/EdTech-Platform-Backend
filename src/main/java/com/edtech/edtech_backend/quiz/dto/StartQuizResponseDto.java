package com.edtech.edtech_backend.quiz.dto;

import com.edtech.edtech_backend.entity.Quiz;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StartQuizResponseDto {

    private Long quizId;
    private String subjectName;
    private int durationMinutes;

    public static StartQuizResponseDto from(Quiz quiz) {
        return StartQuizResponseDto.builder()
                .quizId(quiz.getId())
                .subjectName(quiz.getSubjectName())
                .durationMinutes(quiz.getDurationMinutes())
                .build();
    }
}
