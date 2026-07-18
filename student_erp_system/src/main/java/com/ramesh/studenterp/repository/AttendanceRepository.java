package com.ramesh.studenterp.repository;

import com.ramesh.studenterp.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    boolean existsByEnrollmentIdAndAttendanceDate(
            Long enrollmentId,
            LocalDate attendanceDate
    );

    List<Attendance> findByEnrollmentStudentId(Long studentId);

    List<Attendance> findByTeacherId(Long teacherId);
}
