package com.ramesh.studenterp.controller;

import com.ramesh.studenterp.dto.request.CreateSubjectRequest;
import com.ramesh.studenterp.dto.response.SubjectResponse;
import com.ramesh.studenterp.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectResponse> createSubject(@Valid @RequestBody CreateSubjectRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Long id){

        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SubjectResponse>> getAllSubjects(){

        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectResponse> updateSubject(@PathVariable Long id, @Valid @RequestBody CreateSubjectRequest request){

        return ResponseEntity.ok(subjectService.updateSubject(id, request));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id){

        subjectService.deleteSubject(id);

        return ResponseEntity.noContent().build();
    }



}
