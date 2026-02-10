package com.edtech.edtech_backend.quiz.service;

import com.edtech.edtech_backend.quiz.dto.CreateQuizDto;
import com.edtech.edtech_backend.quiz.dto.QuizResponseDto;

import java.util.List;

public interface QuizService {

    QuizResponseDto createQuiz(CreateQuizDto dto);

    List<QuizResponseDto> getQuizzesByClass(String classGrade);
}
