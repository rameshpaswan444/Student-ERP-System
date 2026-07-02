package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateSubjectRequest;
import com.ramesh.studenterp.dto.response.SubjectResponse;
import com.ramesh.studenterp.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    Subject toEntity(CreateSubjectRequest request);

    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(
            target = "teacherName",
            expression = "java(subject.getTeacher().getUser().getFirstName() + \" \" + subject.getTeacher().getUser().getLastName())"
    )
    SubjectResponse toResponse(Subject subject);
}
