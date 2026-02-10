package com.edtech.edtech_backend.entity;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "subjects",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "classGrade"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassGrade classGrade;

    @Column(nullable = false)
    private boolean isActive = true;
}
