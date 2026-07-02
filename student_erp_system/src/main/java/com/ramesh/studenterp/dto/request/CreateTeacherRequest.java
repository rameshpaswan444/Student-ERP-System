package com.ramesh.studenterp.dto.request;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTeacherRequest {

    @NotBlank
    private String employeeId;

    @NotBlank
    private String qualification;

    @NotBlank
    private String specialization;

    @NotNull
    private LocalDate joiningDate;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal salary;

    private String address;

    @NotNull
    private Long userId;
}
