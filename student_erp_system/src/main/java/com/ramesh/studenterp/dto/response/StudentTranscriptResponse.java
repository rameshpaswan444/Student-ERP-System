package com.ramesh.studenterp.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentTranscriptResponse {

    private Long studentId;

    private String studentName;

    private String rollNumber;

    private String admissionNumber;

    private Double gpa;

    private List<TranscriptSubjectResponse> subjects;
}
