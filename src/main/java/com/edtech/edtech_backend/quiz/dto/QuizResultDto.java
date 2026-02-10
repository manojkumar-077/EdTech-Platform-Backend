package com.edtech.edtech_backend.quiz.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizResultDto {

    private int totalQuestions;
    private int correctAnswers;
    private int score;
}
