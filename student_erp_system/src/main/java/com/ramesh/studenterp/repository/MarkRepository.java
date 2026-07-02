package com.ramesh.studenterp.repository;

import com.ramesh.studenterp.entity.Mark;
import com.ramesh.studenterp.enums.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {

    boolean existsByEnrollmentIdAndExamType(
            Long enrollmentId,
            ExamType examType
    );

    List<Mark> findByEnrollmentStudentId(Long studentId);
}
