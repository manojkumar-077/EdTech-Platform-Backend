package com.edtech.edtech_backend.syllabus.dto;

import com.edtech.edtech_backend.entity.Subject;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubjectResponseDto {

    private Long id;
    private String name;
    private String classGrade;

    public static SubjectResponseDto from(Subject subject) {
        return SubjectResponseDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .classGrade(subject.getClassGrade().name())
                .build();
    }
}
