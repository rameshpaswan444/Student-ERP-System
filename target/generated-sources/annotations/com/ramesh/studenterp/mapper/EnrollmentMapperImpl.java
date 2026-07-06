package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateEnrollmentRequest;
import com.ramesh.studenterp.dto.response.EnrollmentResponse;
import com.ramesh.studenterp.entity.Enrollment;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.entity.Subject;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-06T15:40:12+0545",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class EnrollmentMapperImpl implements EnrollmentMapper {

    @Override
    public Enrollment toEntity(CreateEnrollmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Enrollment.EnrollmentBuilder enrollment = Enrollment.builder();

        enrollment.enrollmentDate( request.getEnrollmentDate() );
        enrollment.semester( request.getSemester() );
        enrollment.academicYear( request.getAcademicYear() );

        return enrollment.build();
    }

    @Override
    public EnrollmentResponse toResponse(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }

        EnrollmentResponse.EnrollmentResponseBuilder enrollmentResponse = EnrollmentResponse.builder();

        enrollmentResponse.studentId( enrollmentStudentId( enrollment ) );
        enrollmentResponse.subjectId( enrollmentSubjectId( enrollment ) );
        enrollmentResponse.subjectName( enrollmentSubjectSubjectName( enrollment ) );
        enrollmentResponse.id( enrollment.getId() );
        enrollmentResponse.enrollmentDate( enrollment.getEnrollmentDate() );
        enrollmentResponse.semester( enrollment.getSemester() );
        enrollmentResponse.academicYear( enrollment.getAcademicYear() );
        enrollmentResponse.grade( enrollment.getGrade() );
        enrollmentResponse.status( enrollment.getStatus() );

        enrollmentResponse.studentName( enrollment.getStudent().getUser().getFirstName() + " " + enrollment.getStudent().getUser().getLastName() );

        return enrollmentResponse.build();
    }

    private Long enrollmentStudentId(Enrollment enrollment) {
        Student student = enrollment.getStudent();
        if ( student == null ) {
            return null;
        }
        return student.getId();
    }

    private Long enrollmentSubjectId(Enrollment enrollment) {
        Subject subject = enrollment.getSubject();
        if ( subject == null ) {
            return null;
        }
        return subject.getId();
    }

    private String enrollmentSubjectSubjectName(Enrollment enrollment) {
        Subject subject = enrollment.getSubject();
        if ( subject == null ) {
            return null;
        }
        return subject.getSubjectName();
    }
}
