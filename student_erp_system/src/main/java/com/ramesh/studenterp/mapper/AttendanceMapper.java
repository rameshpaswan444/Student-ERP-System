package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.CreateAttendanceRequest;
import com.ramesh.studenterp.dto.response.AttendanceResponse;
import com.ramesh.studenterp.entity.Attendance;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring" )
public interface AttendanceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    Attendance toEntity(CreateAttendanceRequest request);

    @Mapping(target = "enrollmentId", source = "enrollment.id")
    @Mapping(target = "studentId", source = "enrollment.student.id")
    @Mapping(target = "subjectId", source = "enrollment.subject.id")
    @Mapping(target = "subjectName", source = "enrollment.subject.subjectName")

    @Mapping(target = "teacherId",
            source = "teacher.id")
    @Mapping(
            target = "studentName",
            expression = "java(attendance.getEnrollment().getStudent().getUser().getFirstName() + \" \" + attendance.getEnrollment().getStudent().getUser().getLastName())"
    )
    AttendanceResponse toResponse(Attendance attendance);

    @AfterMapping
    default void setAdditionalFields(
            Attendance attendance,
            @MappingTarget AttendanceResponse.AttendanceResponseBuilder builder) {

        builder.studentName(
                attendance.getEnrollment()
                        .getStudent()
                        .getUser()
                        .getFirstName()
                        + " "
                        + attendance.getEnrollment()
                        .getStudent()
                        .getUser()
                        .getLastName()
        );

        builder.subjectName(
                attendance.getEnrollment()
                        .getSubject()
                        .getSubjectName()
        );

        builder.teacherName(
                attendance.getTeacher()
                        .getUser()
                        .getFirstName()
                        + " "
                        + attendance.getTeacher()
                        .getUser()
                        .getLastName()
        );
    }
}
