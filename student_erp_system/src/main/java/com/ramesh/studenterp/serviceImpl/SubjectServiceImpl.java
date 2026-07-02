package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.request.CreateSubjectRequest;
import com.ramesh.studenterp.dto.response.SubjectResponse;
import com.ramesh.studenterp.entity.Subject;
import com.ramesh.studenterp.entity.Teacher;
import com.ramesh.studenterp.exception.DuplicateResourceException;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.mapper.SubjectMapper;
import com.ramesh.studenterp.repository.SubjectRepository;
import com.ramesh.studenterp.repository.TeacherRepository;
import com.ramesh.studenterp.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectResponse createSubject(CreateSubjectRequest request) {

        if (subjectRepository.existsBySubjectCode(request.getSubjectCode())){
            throw new DuplicateResourceException("Subject code already exists.");
        }
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found."));

        Subject subject = subjectMapper.toEntity(request);

        subject.setTeacher(teacher);

        Subject savedSubject = subjectRepository.save(subject);

        return subjectMapper.toResponse(savedSubject);
    }

    @Override
    public SubjectResponse getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found."));

        return subjectMapper.toResponse(subject);
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {

        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toResponse)
                .toList();
    }

    @Override
    public SubjectResponse updateSubject(Long id, CreateSubjectRequest request) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found."));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found."));

        subject.setSubjectName(request.getSubjectName());
        subject.setCreditHours(request.getCreditHours());
        subject.setDescription(request.getDescription());
        subject.setTeacher(teacher);

        Subject updatedSubject = subjectRepository.save(subject);

        return subjectMapper.toResponse(updatedSubject);
    }

    @Override
    public void deleteSubject(Long id) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found."));

        subjectRepository.delete(subject);

    }
}
