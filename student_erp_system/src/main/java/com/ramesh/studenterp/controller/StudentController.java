package com.ramesh.studenterp.controller;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.response.StudentResponse;
import com.ramesh.studenterp.dto.response.StudentTranscriptResponse;
import com.ramesh.studenterp.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse createStudent(
            @Valid @RequestBody CreateStudentRequest request
            ){

        return studentService.createStudent(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/getAllStudent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){

        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id, @Valid @RequestBody CreateStudentRequest request
    ){

        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){

       studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/transcript")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentTranscriptResponse> getTranscript(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentTranscript(id));
    }


}
