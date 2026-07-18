package com.ramesh.studenterp.controller;

import com.ramesh.studenterp.dto.request.CreateAttendanceRequest;
import com.ramesh.studenterp.dto.response.AttendanceResponse;
import com.ramesh.studenterp.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<AttendanceResponse> markAttendance(
            @Valid @RequestBody CreateAttendanceRequest request) {

        AttendanceResponse response =
                attendanceService.markAttendance(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<AttendanceResponse> createAttendance(
//            @Valid @RequestBody CreateAttendanceRequest request) {
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(attendanceService.createAttendance(request));
//    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<AttendanceResponse> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(id));
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
    public ResponseEntity<List<AttendanceResponse>> getStudentAttendance(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService.getStudentAttendance(studentId)
        );
    }

    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<List<AttendanceResponse>> getTeacherAttendance(
            @PathVariable Long teacherId) {

        return ResponseEntity.ok(
                attendanceService.getTeacherAttendance(teacherId)
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AttendanceResponse> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody CreateAttendanceRequest request) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.ok("Attendance deleted Successfully.");
    }
}
