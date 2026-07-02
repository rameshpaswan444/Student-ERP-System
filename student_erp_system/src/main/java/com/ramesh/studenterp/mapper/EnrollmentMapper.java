package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateEnrollmentRequest;
import com.ramesh.studenterp.dto.response.EnrollmentResponse;
import com.ramesh.studenterp.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "grade", ignore = true)
    Enrollment toEntity(CreateEnrollmentRequest request);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "subjectName", source = "subject.subjectName")
    @Mapping(
            target = "studentName",
            expression = "java(enrollment.getStudent().getUser().getFirstName() + \" \" + enrollment.getStudent().getUser().getLastName())"
    )
    EnrollmentResponse toResponse(Enrollment enrollment);
}
