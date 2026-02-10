package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.Quiz;
import com.edtech.edtech_backend.common.enums.ClassGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByClassGrade(ClassGrade classGrade);
}
