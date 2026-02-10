package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.Material;
import com.edtech.edtech_backend.entity.Subject;
import com.edtech.edtech_backend.common.enums.ClassGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByClassGradeAndIsActiveTrue(ClassGrade classGrade);

    Optional<Subject> findByNameAndClassGrade(String name, ClassGrade classGrade);


}

