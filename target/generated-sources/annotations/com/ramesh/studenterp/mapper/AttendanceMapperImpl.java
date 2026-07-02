package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateAttendanceRequest;
import com.ramesh.studenterp.dto.response.AttendanceResponse;
import com.ramesh.studenterp.entity.Attendance;
import com.ramesh.studenterp.entity.Enrollment;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.entity.Subject;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-02T17:15:14+0545",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public Attendance toEntity(CreateAttendanceRequest request) {
        if ( request == null ) {
            return null;
        }

        Attendance.AttendanceBuilder attendance = Attendance.builder();

        attendance.attendanceDate( request.getAttendanceDate() );
        attendance.status( request.getStatus() );
        attendance.remarks( request.getRemarks() );

        return attendance.build();
    }

    @Override
    public AttendanceResponse toResponse(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        AttendanceResponse.AttendanceResponseBuilder attendanceResponse = AttendanceResponse.builder();

        attendanceResponse.enrollmentId( attendanceEnrollmentId( attendance ) );
        attendanceResponse.studentId( attendanceEnrollmentStudentId( attendance ) );
        attendanceResponse.subjectId( attendanceEnrollmentSubjectId( attendance ) );
        attendanceResponse.subjectName( attendanceEnrollmentSubjectSubjectName( attendance ) );
        attendanceResponse.id( attendance.getId() );
        attendanceResponse.attendanceDate( attendance.getAttendanceDate() );
        attendanceResponse.status( attendance.getStatus() );
        attendanceResponse.remarks( attendance.getRemarks() );

        attendanceResponse.studentName( attendance.getEnrollment().getStudent().getUser().getFirstName() + " " + attendance.getEnrollment().getStudent().getUser().getLastName() );

        return attendanceResponse.build();
    }

    private Long attendanceEnrollmentId(Attendance attendance) {
        Enrollment enrollment = attendance.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        return enrollment.getId();
    }

    private Long attendanceEnrollmentStudentId(Attendance attendance) {
        Enrollment enrollment = attendance.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        Student student = enrollment.getStudent();
        if ( student == null ) {
            return null;
        }
        return student.getId();
    }

    private Long attendanceEnrollmentSubjectId(Attendance attendance) {
        Enrollment enrollment = attendance.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        Subject subject = enrollment.getSubject();
        if ( subject == null ) {
            return null;
        }
        return subject.getId();
    }

    private String attendanceEnrollmentSubjectSubjectName(Attendance attendance) {
        Enrollment enrollment = attendance.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        Subject subject = enrollment.getSubject();
        if ( subject == null ) {
            return null;
        }
        return subject.getSubjectName();
    }
}
