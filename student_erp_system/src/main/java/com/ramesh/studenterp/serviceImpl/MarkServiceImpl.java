package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.request.CreateMarkRequest;
import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.response.MarkResponse;
import com.ramesh.studenterp.dto.response.StudentResponse;
import com.ramesh.studenterp.dto.response.StudentTranscriptResponse;
import com.ramesh.studenterp.dto.response.TranscriptSubjectResponse;
import com.ramesh.studenterp.entity.Enrollment;
import com.ramesh.studenterp.entity.Mark;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.entity.User;
import com.ramesh.studenterp.enums.Grade;
import com.ramesh.studenterp.enums.StudentStatus;
import com.ramesh.studenterp.exception.DuplicateResourceException;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.mapper.MarkMapper;
import com.ramesh.studenterp.mapper.StudentMapper;
import com.ramesh.studenterp.repository.EnrollmentRepository;
import com.ramesh.studenterp.repository.MarkRepository;
import com.ramesh.studenterp.repository.StudentRepository;
import com.ramesh.studenterp.repository.UserRepository;
import com.ramesh.studenterp.service.MarkService;
import com.ramesh.studenterp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final MarkMapper markMapper;


    private Grade calculateGrade(double percentage){

        if(percentage >= 90)
            return Grade.A_PLUS;

        if(percentage >= 80)
            return Grade.A;

        if(percentage >= 70)
            return Grade.B_PLUS;

        if(percentage >= 60)
            return Grade.B;

        if(percentage >= 50)
            return Grade.C;

        if(percentage >= 40)
            return Grade.D;

        return Grade.E;
    }



    @Override
    public MarkResponse createMark(CreateMarkRequest request) {
        if(markRepository.existsByEnrollmentIdAndExamType(
                request.getEnrollmentId(),
                request.getExamType())){

            throw new DuplicateResourceException(
                    "Marks already exist for this exam.");
        }

        if(request.getObtainedMarks() >
                request.getTotalMarks()){

            throw new IllegalArgumentException(
                    "Obtained marks cannot be greater than total marks.");
        }

        Enrollment enrollment =
                enrollmentRepository.findById(
                                request.getEnrollmentId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found."));

        Mark mark = markMapper.toEntity(request);

        mark.setEnrollment(enrollment);

        double percentage =
                (request.getObtainedMarks()
                        / request.getTotalMarks()) * 100;

        mark.setGrade(calculateGrade(percentage));

        Mark savedMark =
                markRepository.save(mark);

        return markMapper.toResponse(savedMark);


    }

    @Override
    public MarkResponse getMarkById(Long id) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Mark not found."));

        return markMapper.toResponse(mark);
    }

    @Override
    public List<MarkResponse> getAllMarks() {
        return markRepository.findAll()
                .stream()
                .map(markMapper::toResponse)
                .toList();
    }

    @Override
    public MarkResponse updateMark(Long id, CreateMarkRequest request) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Mark not found."));

        if(request.getObtainedMarks() >
                request.getTotalMarks()){

            throw new IllegalArgumentException(
                    "Obtained marks cannot be greater than total marks.");
        }

        mark.setExamType(request.getExamType());
        mark.setObtainedMarks(request.getObtainedMarks());
        mark.setTotalMarks(request.getTotalMarks());
        mark.setRemarks(request.getRemarks());

        double percentage =
                (request.getObtainedMarks()
                        / request.getTotalMarks()) * 100;

        mark.setGrade(calculateGrade(percentage));

        Mark saved =
                markRepository.save(mark);

        return markMapper.toResponse(saved);
    }

    @Override
    public void deleteMark(Long id) {

        Mark mark = markRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Mark not found."));

        markRepository.delete(mark);

    }
}
