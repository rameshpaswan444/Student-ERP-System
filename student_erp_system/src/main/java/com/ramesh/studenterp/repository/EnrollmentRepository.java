package com.ramesh.studenterp.repository;

import com.ramesh.studenterp.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentIdAndSubjectId(Long id, Long subjectId);
}
