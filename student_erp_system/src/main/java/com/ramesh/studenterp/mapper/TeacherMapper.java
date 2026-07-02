package com.ramesh.studenterp.mapper;
import com.ramesh.studenterp.dto.request.CreateTeacherRequest;
import com.ramesh.studenterp.dto.response.TeacherResponse;
import com.ramesh.studenterp.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    Teacher toEntity(CreateTeacherRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "teacherName",
            expression = "java(teacher.getUser().getFirstName() + \" \" + teacher.getUser().getLastName())")
    TeacherResponse toResponse(Teacher teacher);
}
