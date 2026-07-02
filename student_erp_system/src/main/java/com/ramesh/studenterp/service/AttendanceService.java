package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.CreateAttendanceRequest;
import com.ramesh.studenterp.dto.response.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse createAttendance(CreateAttendanceRequest request);

    AttendanceResponse getAttendanceById(Long id);

    List<AttendanceResponse> getAllAttendance();

    AttendanceResponse updateAttendance(Long id,
                                        CreateAttendanceRequest request);

    void deleteAttendance(Long id);
}
