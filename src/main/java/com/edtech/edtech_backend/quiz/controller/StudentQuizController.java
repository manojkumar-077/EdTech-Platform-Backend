package com.edtech.edtech_backend.quiz.controller;

import com.edtech.edtech_backend.quiz.dto.*;
import com.edtech.edtech_backend.quiz.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/quizzes")
@RequiredArgsConstructor
public class StudentQuizController {

    private final QuizAttemptService quizAttemptService;

    // STUDENT → START QUIZ
    @PostMapping("/{quizId}/start")
    public ResponseEntity<StartQuizResponseDto> startQuiz(
            @PathVariable Long quizId) {
        return ResponseEntity.ok(quizAttemptService.startQuiz(quizId));
    }

    // STUDENT → SUBMIT QUIZ
    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizResultDto> submitQuiz(
            @PathVariable Long quizId,
            @RequestBody SubmitQuizDto dto) {
        return ResponseEntity.ok(
                quizAttemptService.submitQuiz(quizId, dto)
        );
    }
}
