package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateMarkRequest;
import com.ramesh.studenterp.dto.response.MarkResponse;
import com.ramesh.studenterp.entity.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarkMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grade", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    Mark toEntity(CreateMarkRequest request);

    @Mapping(target = "enrollmentId", source = "enrollment.id")
    @Mapping(target = "studentId", source = "enrollment.student.id")
    @Mapping(target = "subjectId", source = "enrollment.subject.id")
    @Mapping(target = "subjectName", source = "enrollment.subject.subjectName")
    @Mapping(target = "obtainedMarks", source = "obtainedMarks")


    @Mapping(
            target = "studentName",
            expression = "java(mark.getEnrollment().getStudent().getUser().getFirstName() + \" \" + mark.getEnrollment().getStudent().getUser().getLastName())"
    )

    @Mapping(
            target = "percentage",
            expression = "java((mark.getObtainedMarks() / mark.getTotalMarks()) * 100)"
    )
    MarkResponse toResponse(Mark mark);
}
