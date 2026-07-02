package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.request.CreateSubjectRequest;
import com.ramesh.studenterp.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {

    SubjectResponse createSubject(CreateSubjectRequest request);

    SubjectResponse getSubjectById(Long id);

    List<SubjectResponse> getAllSubjects();

    SubjectResponse updateSubject(Long id, CreateSubjectRequest request);

    void deleteSubject(Long id);
}
