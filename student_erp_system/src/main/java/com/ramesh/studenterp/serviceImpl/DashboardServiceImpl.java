package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.response.DashboardResponse;
import com.ramesh.studenterp.repository.*;
import com.ramesh.studenterp.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {


    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final MarkRepository markRepository;


    @Override
    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalStudents(studentRepository.count())
                .totalTeachers(teacherRepository.count())
                .totalSubjects(subjectRepository.count())
                .totalEnrollments(enrollmentRepository.count())
                .totalAttendance(attendanceRepository.count())
                .totalMarks(markRepository.count())
                .build();
    }
}
