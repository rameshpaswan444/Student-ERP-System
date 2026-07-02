package com.ramesh.studenterp.entity;

import com.ramesh.studenterp.enums.ExamType;
import com.ramesh.studenterp.enums.Grade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamType examType;

    @Column(nullable = false)
    private Double obtainedMarks;

    @Column(nullable = false)
    private Double totalMarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;
}
