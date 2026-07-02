package com.ramesh.studenterp.dto.request;

import com.ramesh.studenterp.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotBlank
    private String admissionNumber;

    @NotBlank
    private String rollNumber;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    private String bloodGroup;

    @NotNull
    private LocalDate admissionDate;

    private String address;

    @NotNull
    private Long userId;
}
