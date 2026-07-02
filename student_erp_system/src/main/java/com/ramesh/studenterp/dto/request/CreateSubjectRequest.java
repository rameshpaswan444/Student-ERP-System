package com.ramesh.studenterp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSubjectRequest {

    @NotBlank
    private String subjectCode;

    @NotBlank
    private String subjectName;

    @NotNull
    private Integer creditHours;

    private String description;

    @NotNull
    private Long teacherId;
}
