package com.edtech.edtech_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "student_answers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"quiz_attempt_id", "question_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_attempt_id", nullable = false)
    private QuizAttempt quizAttempt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private int selectedOptionIndex;
}
