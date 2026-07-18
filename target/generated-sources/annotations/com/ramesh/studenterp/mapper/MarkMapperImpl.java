package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateMarkRequest;
import com.ramesh.studenterp.dto.response.MarkResponse;
import com.ramesh.studenterp.entity.Enrollment;
import com.ramesh.studenterp.entity.Mark;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.entity.Subject;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T23:22:27+0545",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class MarkMapperImpl implements MarkMapper {

    @Override
    public Mark toEntity(CreateMarkRequest request) {
        if ( request == null ) {
            return null;
        }

        Mark.MarkBuilder mark = Mark.builder();

        mark.examType( request.getExamType() );
        mark.obtainedMarks( request.getObtainedMarks() );
        mark.totalMarks( request.getTotalMarks() );
        mark.remarks( request.getRemarks() );

        return mark.build();
    }

    @Override
    public MarkResponse toResponse(Mark mark) {
        if ( mark == null ) {
            return null;
        }

        MarkResponse.MarkResponseBuilder markResponse = MarkResponse.builder();

        markResponse.enrollmentId( markEnrollmentId( mark ) );
        markResponse.studentId( markEnrollmentStudentId( mark ) );
        markResponse.subjectId( markEnrollmentSubjectId( mark ) );
        markResponse.subjectName( markEnrollmentSubjectSubjectName( mark ) );
        markResponse.obtainedMarks( mark.getObtainedMarks() );
        markResponse.id( mark.getId() );
        markResponse.examType( mark.getExamType() );
        markResponse.totalMarks( mark.getTotalMarks() );
        markResponse.grade( mark.getGrade() );
        markResponse.remarks( mark.getRemarks() );

        markResponse.studentName( mark.getEnrollment().getStudent().getUser().getFirstName() + " " + mark.getEnrollment().getStudent().getUser().getLastName() );
        markResponse.percentage( (mark.getObtainedMarks() / mark.getTotalMarks()) * 100 );

        setAdditionalFields( mark, markResponse );

        return markResponse.build();
    }

    private Long markEnrollmentId(Mark mark) {
        Enrollment enrollment = mark.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        return enrollment.getId();
    }

    private Long markEnrollmentStudentId(Mark mark) {
        Enrollment enrollment = mark.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        Student student = enrollment.getStudent();
        if ( student == null ) {
            return null;
        }
        return student.getId();
    }

    private Long markEnrollmentSubjectId(Mark mark) {
        Enrollment enrollment = mark.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        Subject subject = enrollment.getSubject();
        if ( subject == null ) {
            return null;
        }
        return subject.getId();
    }

    private String markEnrollmentSubjectSubjectName(Mark mark) {
        Enrollment enrollment = mark.getEnrollment();
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
