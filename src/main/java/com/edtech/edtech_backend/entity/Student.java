package com.edtech.edtech_backend.entity;

import com.edtech.edtech_backend.common.enums.ClassGrade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassGrade classGrade;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String schoolName;

    private String fatherName;

    private String fatherPhone;

    @Column(length = 500)
    private String address;

    private String avatarUrl;
}
