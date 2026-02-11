package com.edtech.edtech_backend.quiz.service;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import com.edtech.edtech_backend.entity.Quiz;
import com.edtech.edtech_backend.repository.QuizRepository;
import com.edtech.edtech_backend.quiz.dto.CreateQuizDto;
import com.edtech.edtech_backend.quiz.dto.QuizResponseDto;
import com.edtech.edtech_backend.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public QuizResponseDto createQuiz(CreateQuizDto dto) {

        Quiz quiz = Quiz.builder()
                .classGrade(dto.getClassGrade())
                .subjectName(dto.getSubjectName())
                .durationMinutes(dto.getDurationMinutes())
                .build();

        quizRepository.save(quiz);
        return QuizResponseDto.from(quiz);
    }

    @Override
    public List<QuizResponseDto> getQuizzesByClass(String classGrade) {

        ClassGrade grade = ClassGrade.valueOf(classGrade.toUpperCase());

        return quizRepository.findByClassGrade(grade)
                .stream()
                .map(QuizResponseDto::from)
                .collect(Collectors.toList());
    }
}
