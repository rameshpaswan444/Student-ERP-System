package com.ramesh.studenterp.dto.request;
import com.ramesh.studenterp.enums.Semester;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEnrollmentRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long subjectId;

    @NotNull
    private LocalDate enrollmentDate;

    @NotNull
    private Semester semester;

    @NotBlank
    private String academicYear;
}
