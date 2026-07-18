package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.CreateAttendanceRequest;
import com.ramesh.studenterp.dto.response.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse markAttendance(CreateAttendanceRequest request);

    AttendanceResponse createAttendance(CreateAttendanceRequest request);

    AttendanceResponse getAttendanceById(Long id);

    List<AttendanceResponse> getAllAttendance();

    List<AttendanceResponse> getStudentAttendance(Long studentId);

    List<AttendanceResponse> getTeacherAttendance(Long teacherId);

    AttendanceResponse updateAttendance(Long id,
                                        CreateAttendanceRequest request);

    void deleteAttendance(Long id);
}
