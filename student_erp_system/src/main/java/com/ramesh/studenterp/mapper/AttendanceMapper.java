package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateAttendanceRequest;
import com.ramesh.studenterp.dto.response.AttendanceResponse;
import com.ramesh.studenterp.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface AttendanceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    Attendance toEntity(CreateAttendanceRequest request);

    @Mapping(target = "enrollmentId", source = "enrollment.id")
    @Mapping(target = "studentId", source = "enrollment.student.id")
    @Mapping(target = "subjectId", source = "enrollment.subject.id")
    @Mapping(target = "subjectName", source = "enrollment.subject.subjectName")
    @Mapping(
            target = "studentName",
            expression = "java(attendance.getEnrollment().getStudent().getUser().getFirstName() + \" \" + attendance.getEnrollment().getStudent().getUser().getLastName())"
    )
    AttendanceResponse toResponse(Attendance attendance);
}
