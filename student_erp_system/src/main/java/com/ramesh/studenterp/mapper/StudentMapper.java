package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.response.StudentResponse;
import com.ramesh.studenterp.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    Student toEntity(CreateStudentRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "studentName",
            expression = "java(student.getUser().getFirstName() + \" \" + student.getUser().getLastName())")
    @Mapping(target = "email", source = "user.email")
    StudentResponse toResponse(Student student);
}
