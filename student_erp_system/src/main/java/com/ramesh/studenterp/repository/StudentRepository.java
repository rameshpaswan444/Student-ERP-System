package com.ramesh.studenterp.repository;

import com.ramesh.studenterp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByAdmissionNumber(String admissionNumber);

    boolean existsByRollNumber(String rollNumber);

    Optional<Student> findByAdmissionNumber(String admissionNumber);
}
