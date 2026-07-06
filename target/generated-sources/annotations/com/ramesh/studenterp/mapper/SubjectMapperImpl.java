package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateSubjectRequest;
import com.ramesh.studenterp.dto.response.SubjectResponse;
import com.ramesh.studenterp.entity.Subject;
import com.ramesh.studenterp.entity.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-06T15:40:12+0545",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public Subject toEntity(CreateSubjectRequest request) {
        if ( request == null ) {
            return null;
        }

        Subject.SubjectBuilder subject = Subject.builder();

        subject.subjectCode( request.getSubjectCode() );
        subject.subjectName( request.getSubjectName() );
        subject.creditHours( request.getCreditHours() );
        subject.description( request.getDescription() );

        return subject.build();
    }

    @Override
    public SubjectResponse toResponse(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectResponse.SubjectResponseBuilder subjectResponse = SubjectResponse.builder();

        subjectResponse.teacherId( subjectTeacherId( subject ) );
        subjectResponse.id( subject.getId() );
        subjectResponse.subjectCode( subject.getSubjectCode() );
        subjectResponse.subjectName( subject.getSubjectName() );
        subjectResponse.creditHours( subject.getCreditHours() );
        subjectResponse.description( subject.getDescription() );

        subjectResponse.teacherName( subject.getTeacher().getUser().getFirstName() + " " + subject.getTeacher().getUser().getLastName() );

        return subjectResponse.build();
    }

    private Long subjectTeacherId(Subject subject) {
        Teacher teacher = subject.getTeacher();
        if ( teacher == null ) {
            return null;
        }
        return teacher.getId();
    }
}
