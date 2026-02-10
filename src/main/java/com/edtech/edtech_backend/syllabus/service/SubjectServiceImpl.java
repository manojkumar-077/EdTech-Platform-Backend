package com.edtech.edtech_backend.syllabus.service;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import com.edtech.edtech_backend.entity.Subject;
import com.edtech.edtech_backend.repository.SubjectRepository;
import com.edtech.edtech_backend.syllabus.dto.CreateSubjectDto;
import com.edtech.edtech_backend.syllabus.dto.SubjectResponseDto;
import com.edtech.edtech_backend.syllabus.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public SubjectResponseDto createSubject(CreateSubjectDto dto) {

        Subject subject = Subject.builder()
                .name(dto.getName())
                .classGrade(dto.getClassGrade())
                .build();

        subjectRepository.save(subject);
        return SubjectResponseDto.from(subject);
    }

    @Override
    public List<SubjectResponseDto> getSubjectsByClass(String classGrade) {

        ClassGrade grade = ClassGrade.valueOf(classGrade);

        return subjectRepository.findByClassGradeAndIsActiveTrue(grade)
                .stream()
                .map(SubjectResponseDto::from)
                .collect(Collectors.toList());

    }
}
