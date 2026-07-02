package com.ramesh.studenterp.dto.response;

import com.ramesh.studenterp.enums.ExamType;
import com.ramesh.studenterp.enums.Grade;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranscriptSubjectResponse {

    private String subjectName;

    private ExamType examType;

    private Double obtainedMarks;

    private Double totalMarks;

    private Double percentage;

    private Grade grade;
}
