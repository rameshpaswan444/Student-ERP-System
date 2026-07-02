package com.ramesh.studenterp.controller;

import com.ramesh.studenterp.dto.request.CreateMarkRequest;
import com.ramesh.studenterp.dto.response.MarkResponse;
import com.ramesh.studenterp.service.MarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarkResponse> createMark(
            @Valid @RequestBody CreateMarkRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(markService.createMark(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarkResponse> getMarkById(
            @PathVariable Long id) {

        return ResponseEntity.ok(markService.getMarkById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MarkResponse>> getAllMarks() {

        return ResponseEntity.ok(markService.getAllMarks());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarkResponse> updateMark(
            @PathVariable Long id,
            @Valid @RequestBody CreateMarkRequest request) {

        return ResponseEntity.ok(markService.updateMark(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMark(
            @PathVariable Long id) {

        markService.deleteMark(id);

        return ResponseEntity.noContent().build();
    }
}
