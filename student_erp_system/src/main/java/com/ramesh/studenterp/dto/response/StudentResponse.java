package com.ramesh.studenterp.dto.response;

import com.ramesh.studenterp.enums.Gender;
import com.ramesh.studenterp.enums.StudentStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long id;

    private String admissionNumber;

    private String rollNumber;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String bloodGroup;

    private LocalDate admissionDate;

    private String address;

    private StudentStatus status;

    private Long userId;

    private String studentName;

    private String email;
}
