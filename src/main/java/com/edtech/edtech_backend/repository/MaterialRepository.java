package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.Material;
import com.edtech.edtech_backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findBySubjectId(Long subjectId);
}
