package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.CreateEnrollmentRequest;
import com.ramesh.studenterp.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse createEnrollment(CreateEnrollmentRequest request);

    EnrollmentResponse getEnrollmentById(Long id);

    List<EnrollmentResponse> getAllEnrollments();

    EnrollmentResponse updateEnrollment(Long id, CreateEnrollmentRequest request);

    void deleteEnrollment(Long id);
}
