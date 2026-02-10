package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.QuizAttempt;
import com.edtech.edtech_backend.entity.Student;
import com.edtech.edtech_backend.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    Optional<QuizAttempt> findByStudentAndQuiz(Student student, Quiz quiz);

    List<QuizAttempt> findByStudent(Student student);
}
