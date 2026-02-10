package com.edtech.edtech_backend.quiz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SubmitQuizDto {

    // key = questionId, value = selectedOptionId
    private Map<Long, Long> answers;
}
