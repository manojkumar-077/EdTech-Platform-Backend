package com.edtech.edtech_backend.quiz.controller;

import com.edtech.edtech_backend.quiz.dto.CreateQuizDto;
import com.edtech.edtech_backend.quiz.dto.QuizResponseDto;
import com.edtech.edtech_backend.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/quizzes")
@RequiredArgsConstructor
public class AdminQuizController {

    private final QuizService quizService;

    // ADMIN → CREATE QUIZ
    @PostMapping
    public ResponseEntity<QuizResponseDto> createQuiz(
            @RequestBody CreateQuizDto dto) {
        return ResponseEntity.ok(quizService.createQuiz(dto));
    }

    // ADMIN → VIEW QUIZZES BY CLASS
    @GetMapping("/{classGrade}")
    public ResponseEntity<List<QuizResponseDto>> getQuizzes(
            @PathVariable String classGrade) {
        return ResponseEntity.ok(quizService.getQuizzesByClass(classGrade));
    }
}
