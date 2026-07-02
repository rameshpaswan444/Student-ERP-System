package com.ramesh.studenterp.dto.response;
import com.ramesh.studenterp.enums.TeacherStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponse {

    private Long id;

    private String employeeId;

    private String qualification;

    private String specialization;

    private LocalDate joiningDate;

    private BigDecimal salary;

    private String address;

    private TeacherStatus status;

    private Long userId;

    private String teacherName;

    private String email;
}
