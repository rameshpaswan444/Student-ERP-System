package com.ramesh.studenterp.service;

import com.ramesh.studenterp.dto.request.CreateMarkRequest;
import com.ramesh.studenterp.dto.response.MarkResponse;

import java.util.List;

public interface MarkService {

    MarkResponse createMark(CreateMarkRequest request);

    MarkResponse getMarkById(Long id);

    List<MarkResponse> getAllMarks();

    MarkResponse updateMark(Long id, CreateMarkRequest request);

    void deleteMark(Long id);
}
