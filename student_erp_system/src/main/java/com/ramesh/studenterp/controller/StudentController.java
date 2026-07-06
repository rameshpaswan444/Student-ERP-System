package com.ramesh.studenterp.controller;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.response.StudentResponse;
import com.ramesh.studenterp.dto.response.StudentTranscriptResponse;
import com.ramesh.studenterp.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
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

    @GetMapping("/{id}/transcript/pdf")
    public ResponseEntity<InputStreamResource> downloadTranscript(
            @PathVariable Long id) {

        ByteArrayInputStream pdf =
                studentService.downloadTranscript(id);

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=transcript.pdf"
        );

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @PostMapping("/{id}/transcript/email")
    public ResponseEntity<String> emailTranscript(
            @PathVariable Long id) {

        studentService.emailTranscript(id);

        return ResponseEntity.ok(
                "Transcript emailed successfully."
        );
    }


}
