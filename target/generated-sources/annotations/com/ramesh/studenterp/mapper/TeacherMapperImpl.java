package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateTeacherRequest;
import com.ramesh.studenterp.dto.response.TeacherResponse;
import com.ramesh.studenterp.entity.Teacher;
import com.ramesh.studenterp.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-02T17:06:03+0545",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher toEntity(CreateTeacherRequest request) {
        if ( request == null ) {
            return null;
        }

        Teacher.TeacherBuilder teacher = Teacher.builder();

        teacher.employeeId( request.getEmployeeId() );
        teacher.qualification( request.getQualification() );
        teacher.specialization( request.getSpecialization() );
        teacher.joiningDate( request.getJoiningDate() );
        teacher.salary( request.getSalary() );
        teacher.address( request.getAddress() );

        return teacher.build();
    }

    @Override
    public TeacherResponse toResponse(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherResponse.TeacherResponseBuilder teacherResponse = TeacherResponse.builder();

        teacherResponse.userId( teacherUserId( teacher ) );
        teacherResponse.email( teacherUserEmail( teacher ) );
        teacherResponse.id( teacher.getId() );
        teacherResponse.employeeId( teacher.getEmployeeId() );
        teacherResponse.qualification( teacher.getQualification() );
        teacherResponse.specialization( teacher.getSpecialization() );
        teacherResponse.joiningDate( teacher.getJoiningDate() );
        teacherResponse.salary( teacher.getSalary() );
        teacherResponse.address( teacher.getAddress() );
        teacherResponse.status( teacher.getStatus() );

        teacherResponse.teacherName( teacher.getUser().getFirstName() + " " + teacher.getUser().getLastName() );

        return teacherResponse.build();
    }

    private Long teacherUserId(Teacher teacher) {
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private String teacherUserEmail(Teacher teacher) {
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getEmail();
    }
}
