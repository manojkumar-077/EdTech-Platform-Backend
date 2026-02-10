package com.edtech.edtech_backend.quiz.service;

import com.edtech.edtech_backend.entity.Quiz;
import com.edtech.edtech_backend.quiz.dto.*;
import com.edtech.edtech_backend.repository.QuizAttemptRepository;
import com.edtech.edtech_backend.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizAttemptServiceImpl implements QuizAttemptService {

    private final QuizRepository quizRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    public StartQuizResponseDto startQuiz(Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        return StartQuizResponseDto.from(quiz);
    }

    @Override
    public QuizResultDto submitQuiz(Long quizId, SubmitQuizDto dto) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        int totalQuestions = dto.getAnswers().size();
        int correctAnswers = 0; // placeholder
        int score = 0;          // placeholder


        return QuizResultDto.builder()
                .totalQuestions(totalQuestions)
                .correctAnswers(correctAnswers)
                .score(score)
                .build();
    }
}
