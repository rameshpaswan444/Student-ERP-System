package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.request.CreateTeacherRequest;
import com.ramesh.studenterp.dto.response.TeacherResponse;

import java.util.List;

public interface TeacherService {

    TeacherResponse createTeacher(CreateTeacherRequest request);

    TeacherResponse getTeacherById(Long id);

    List<TeacherResponse> getAllTeachers();

    TeacherResponse updateTeacher(Long id, CreateTeacherRequest request);

    void deleteTeacher(Long id);
}
