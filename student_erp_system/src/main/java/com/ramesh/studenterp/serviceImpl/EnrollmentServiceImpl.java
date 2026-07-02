package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.request.CreateEnrollmentRequest;
import com.ramesh.studenterp.dto.response.EnrollmentResponse;
import com.ramesh.studenterp.entity.Enrollment;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.entity.Subject;
import com.ramesh.studenterp.enums.EnrollmentStatus;
import com.ramesh.studenterp.exception.DuplicateResourceException;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.mapper.EnrollmentMapper;
import com.ramesh.studenterp.repository.EnrollmentRepository;
import com.ramesh.studenterp.repository.StudentRepository;
import com.ramesh.studenterp.repository.SubjectRepository;
import com.ramesh.studenterp.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse createEnrollment(CreateEnrollmentRequest request) {

        if (enrollmentRepository.existsByStudentIdAndSubjectId(request.getStudentId(), request.getSubjectId())){
            throw new DuplicateResourceException("Student is already enrolled in this subject.");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(()->new ResourceNotFoundException("Student not found"));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(()-> new ResourceNotFoundException("Subject not found"));

        Enrollment enrollment = enrollmentMapper.toEntity(request);

        enrollment.setStudent(student);
        enrollment.setSubject(subject);

        enrollment.setStatus(EnrollmentStatus.ENROLLED);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(savedEnrollment);
    }

    @Override
    public EnrollmentResponse getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enrollment not found."));

        return enrollmentMapper.toResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toResponse)
                .toList();
    }

    @Override
    public EnrollmentResponse updateEnrollment(Long id, CreateEnrollmentRequest request) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enrollment not found."));

        enrollment.setEnrollmentDate(request.getEnrollmentDate());
        enrollment.setSemester(request.getSemester());
        enrollment.setAcademicYear(request.getAcademicYear());

        Enrollment saved =
                enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(saved);
    }

    @Override
    public void deleteEnrollment(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enrollment not found."));

        enrollmentRepository.delete(enrollment);

    }
}
