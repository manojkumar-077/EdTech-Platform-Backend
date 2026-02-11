package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.Student;
import com.edtech.edtech_backend.common.enums.ClassGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUser_Email(String email);



    List<Student> findByClassGrade(ClassGrade classGrade);
}
