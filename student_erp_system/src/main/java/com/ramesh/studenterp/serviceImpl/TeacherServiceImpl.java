package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.request.CreateTeacherRequest;
import com.ramesh.studenterp.dto.response.TeacherResponse;
import com.ramesh.studenterp.entity.Teacher;
import com.ramesh.studenterp.entity.User;
import com.ramesh.studenterp.enums.TeacherStatus;
import com.ramesh.studenterp.exception.DuplicateResourceException;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.mapper.TeacherMapper;
import com.ramesh.studenterp.repository.TeacherRepository;
import com.ramesh.studenterp.repository.UserRepository;
import com.ramesh.studenterp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherResponse createTeacher(CreateTeacherRequest request) {

        if (teacherRepository.existsByEmployeeId(request.getEmployeeId())){
            throw new DuplicateResourceException("Employee ID already exists.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User not found."));

        Teacher teacher = teacherMapper.toEntity(request);

        teacher.setUser(user);

        teacher.setStatus(TeacherStatus.ACTIVE);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toResponse(savedTeacher);
    }

    @Override
    public TeacherResponse getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher not found."));

        return teacherMapper.toResponse(teacher);
    }

    @Override
    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toResponse)
                .toList();
    }

    @Override
    public TeacherResponse updateTeacher(Long id, CreateTeacherRequest request) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher not found."));

        teacher.setQualification(request.getQualification());
        teacher.setSpecialization(request.getSpecialization());
        teacher.setJoiningDate(request.getJoiningDate());
        teacher.setSalary(request.getSalary());
        teacher.setAddress(request.getAddress());

        Teacher updatedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toResponse(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher not found."));

        teacherRepository.delete(teacher);

    }
}
