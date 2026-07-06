package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.response.StudentResponse;
import com.ramesh.studenterp.dto.response.StudentTranscriptResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface StudentService {

    StudentResponse createStudent(CreateStudentRequest request);

    StudentResponse getStudentById(Long id);

    List<StudentResponse> getAllStudent();

    StudentResponse updateStudent(Long id, CreateStudentRequest request);

    void deleteStudent(Long id);

    StudentTranscriptResponse getStudentTranscript(Long studentId);

    ByteArrayInputStream downloadTranscript(Long studentId);

    void emailTranscript(Long studentId);
}
