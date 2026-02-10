package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.StudentAnswer;
import com.edtech.edtech_backend.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    List<StudentAnswer> findByQuizAttempt(QuizAttempt quizAttempt);
}
