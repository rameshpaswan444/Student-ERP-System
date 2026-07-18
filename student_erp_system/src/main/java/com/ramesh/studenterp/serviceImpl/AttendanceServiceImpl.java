package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.request.CreateAttendanceRequest;
import com.ramesh.studenterp.dto.response.AttendanceResponse;
import com.ramesh.studenterp.entity.Attendance;
import com.ramesh.studenterp.entity.Enrollment;
import com.ramesh.studenterp.entity.Teacher;
import com.ramesh.studenterp.exception.DuplicateResourceException;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.mapper.AttendanceMapper;
import com.ramesh.studenterp.repository.AttendanceRepository;
import com.ramesh.studenterp.repository.EnrollmentRepository;
import com.ramesh.studenterp.repository.TeacherRepository;
import com.ramesh.studenterp.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final TeacherRepository teacherRepository;
    private final AttendanceMapper attendanceMapper;



    @Override
    public AttendanceResponse markAttendance(CreateAttendanceRequest request) {

        if (attendanceRepository.existsByEnrollmentIdAndAttendanceDate(
                request.getEnrollmentId(),
                request.getAttendanceDate())) {

            throw new DuplicateResourceException(
                    "Attendance has already been marked for this student on this date."
            );
        }

        Enrollment enrollment = enrollmentRepository.findById(request.getEnrollmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enrollment not found."));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found."));

        Attendance attendance = attendanceMapper.toEntity(request);

        attendance.setEnrollment(enrollment);
        attendance.setTeacher(teacher);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(savedAttendance);
    }

    @Override
    public AttendanceResponse createAttendance(CreateAttendanceRequest request) {
        if (attendanceRepository.existsByEnrollmentIdAndAttendanceDate(
                request.getEnrollmentId(),
                request.getAttendanceDate())) {

            throw new DuplicateResourceException(
                    "Attendance already marked for this date."
            );
        }

        Enrollment enrollment = enrollmentRepository
                .findById(request.getEnrollmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enrollment not found."));

        Attendance attendance = attendanceMapper.toEntity(request);

        attendance.setEnrollment(enrollment);

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(savedAttendance);
    }

    @Override
    public AttendanceResponse getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found."));

        return attendanceMapper.toResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getStudentAttendance(Long studentId) {
        return attendanceRepository.findByEnrollmentStudentId(studentId)
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getTeacherAttendance(Long teacherId) {
        return attendanceRepository.findByTeacherId(teacherId)
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    @Override
    public AttendanceResponse updateAttendance(Long id, CreateAttendanceRequest request) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found."));

        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setStatus(request.getStatus());
        attendance.setRemarks(request.getRemarks());

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(savedAttendance);
    }

    @Override
    public void deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found."));

        attendanceRepository.delete(attendance);

    }


}
