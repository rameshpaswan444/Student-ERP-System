package com.ramesh.studenterp.dto.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponse {

    private Long id;

    private String subjectCode;

    private String subjectName;

    private Integer creditHours;

    private String description;

    private Long teacherId;

    private String teacherName;
}
