package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.response.StudentResponse;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-03T21:44:45+0545",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toEntity(CreateStudentRequest request) {
        if ( request == null ) {
            return null;
        }

        Student.StudentBuilder student = Student.builder();

        student.admissionNumber( request.getAdmissionNumber() );
        student.rollNumber( request.getRollNumber() );
        student.admissionDate( request.getAdmissionDate() );
        student.address( request.getAddress() );
        student.dateOfBirth( request.getDateOfBirth() );
        student.gender( request.getGender() );
        student.bloodGroup( request.getBloodGroup() );

        return student.build();
    }

    @Override
    public StudentResponse toResponse(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponse.StudentResponseBuilder studentResponse = StudentResponse.builder();

        studentResponse.userId( studentUserId( student ) );
        studentResponse.email( studentUserEmail( student ) );
        studentResponse.id( student.getId() );
        studentResponse.admissionNumber( student.getAdmissionNumber() );
        studentResponse.rollNumber( student.getRollNumber() );
        studentResponse.dateOfBirth( student.getDateOfBirth() );
        studentResponse.gender( student.getGender() );
        studentResponse.bloodGroup( student.getBloodGroup() );
        studentResponse.admissionDate( student.getAdmissionDate() );
        studentResponse.address( student.getAddress() );
        studentResponse.status( student.getStatus() );

        studentResponse.studentName( student.getUser().getFirstName() + " " + student.getUser().getLastName() );

        return studentResponse.build();
    }

    private Long studentUserId(Student student) {
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private String studentUserEmail(Student student) {
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getEmail();
    }
}
