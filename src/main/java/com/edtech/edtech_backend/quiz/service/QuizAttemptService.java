package com.edtech.edtech_backend.quiz.service;

import com.edtech.edtech_backend.quiz.dto.*;

public interface QuizAttemptService {

    StartQuizResponseDto startQuiz(Long quizId);

    QuizResultDto submitQuiz(Long quizId, SubmitQuizDto dto);
}
