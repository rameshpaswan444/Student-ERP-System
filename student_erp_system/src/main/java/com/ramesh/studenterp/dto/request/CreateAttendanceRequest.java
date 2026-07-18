package com.ramesh.studenterp.dto.request;
import com.ramesh.studenterp.enums.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAttendanceRequest {

    @NotNull(message = "Enrollment ID is required.")
    private Long enrollmentId;

    @NotNull(message = "Teacher ID is required.")
    private Long teacherId;

    @NotNull(message = "Attendance date is required.")
    private LocalDate attendanceDate;

    @NotNull(message = "Attendance status is required.")
    private AttendanceStatus status;

    private String remarks;
}
