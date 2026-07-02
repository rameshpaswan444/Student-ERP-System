package com.ramesh.studenterp.dto.response;
import com.ramesh.studenterp.enums.EnrollmentStatus;
import com.ramesh.studenterp.enums.Semester;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {

    private Long id;

    private Long studentId;

    private String studentName;

    private Long subjectId;

    private String subjectName;

    private LocalDate enrollmentDate;

    private Semester semester;

    private String academicYear;

    private String grade;

    private EnrollmentStatus status;
}
