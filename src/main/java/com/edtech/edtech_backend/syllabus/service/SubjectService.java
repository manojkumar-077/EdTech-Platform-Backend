package com.edtech.edtech_backend.syllabus.service;

import com.edtech.edtech_backend.syllabus.dto.CreateSubjectDto;
import com.edtech.edtech_backend.syllabus.dto.SubjectResponseDto;

import java.util.List;

public interface SubjectService {

    SubjectResponseDto createSubject(CreateSubjectDto dto);

    List<SubjectResponseDto> getSubjectsByClass(String classGrade);
}
