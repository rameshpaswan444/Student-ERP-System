package com.ramesh.studenterp.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private long totalStudents;

    private long totalTeachers;

    private long totalSubjects;

    private long totalEnrollments;

    private long totalAttendance;

    private long totalMarks;
}
