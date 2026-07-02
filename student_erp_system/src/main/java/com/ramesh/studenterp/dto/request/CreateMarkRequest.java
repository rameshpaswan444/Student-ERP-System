package com.ramesh.studenterp.dto.request;

import com.ramesh.studenterp.enums.ExamType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMarkRequest {

    @NotNull
    private Long enrollmentId;

    @NotNull
    private ExamType examType;

    @NotNull
    private Double obtainedMarks;

    @NotNull
    private Double totalMarks;

    private String remarks;
}
