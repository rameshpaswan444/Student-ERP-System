package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.dto.request.CreateStudentRequest;
import com.ramesh.studenterp.dto.response.StudentResponse;
import com.ramesh.studenterp.dto.response.StudentTranscriptResponse;
import com.ramesh.studenterp.dto.response.TranscriptSubjectResponse;
import com.ramesh.studenterp.entity.Mark;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.entity.User;
import com.ramesh.studenterp.enums.Grade;
import com.ramesh.studenterp.enums.StudentStatus;
import com.ramesh.studenterp.exception.DuplicateResourceException;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.mapper.StudentMapper;
import com.ramesh.studenterp.repository.MarkRepository;
import com.ramesh.studenterp.repository.StudentRepository;
import com.ramesh.studenterp.repository.UserRepository;
import com.ramesh.studenterp.service.EmailService;
import com.ramesh.studenterp.service.PdfService;
import com.ramesh.studenterp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final MarkRepository markRepository;
    private final StudentMapper studentMapper;
    private final PdfService pdfService;
    private final EmailService emailService;

    @Override
    public StudentResponse createStudent(CreateStudentRequest request) {

        if (studentRepository.existsByAdmissionNumber(request.getAdmissionNumber())){
            throw new DuplicateResourceException("Admission number already exists.");
        }

        if (studentRepository.existsByRollNumber(request.getRollNumber())){
            throw new DuplicateResourceException("Roll number already exists.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User not found."));

        Student student = studentMapper.toEntity(request);

        student.setUser(user);
        student.setStatus(StudentStatus.ACTIVE);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    @Override
    public StudentResponse getStudentById(Long id) {

       Student student = studentRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Student not found."));

       return studentMapper.toResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    public StudentResponse updateStudent(Long id, CreateStudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        student.setAdmissionNumber(request.getAdmissionNumber());
        student.setRollNumber(request.getRollNumber());
        student.setAdmissionDate(request.getAdmissionDate());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setBloodGroup(request.getBloodGroup());
        student.setAddress(request.getAddress());

        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toResponse(updatedStudent);

    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found."));

        studentRepository.delete(student);

    }

    @Override
    public StudentTranscriptResponse getStudentTranscript(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        List<Mark> marks = markRepository.findByEnrollmentStudentId(studentId);

        double gpa = marks.stream()
                .mapToDouble(mark -> getGradePoint(mark.getGrade()))
                .average()
                .orElse(0.0);

        List<TranscriptSubjectResponse> subjects = marks.stream()
                .map(mark -> TranscriptSubjectResponse.builder()
                        .subjectName(mark.getEnrollment()
                                .getSubject()
                                .getSubjectName())
                        .examType(mark.getExamType())
                        .obtainedMarks(mark.getObtainedMarks())
                        .totalMarks(mark.getTotalMarks())
                        .percentage(
                                (mark.getObtainedMarks()
                                        / mark.getTotalMarks()) * 100
                        )
                        .grade(mark.getGrade())
                        .build())
                .toList();

        return StudentTranscriptResponse.builder()
                .studentId(student.getId())
                .studentName(
                        student.getUser().getFirstName()
                                + " "
                                + student.getUser().getLastName()
                )
                .rollNumber(student.getRollNumber())
                .admissionNumber(student.getAdmissionNumber())
                .gpa(gpa)
                .subjects(subjects)
                .build();
    }

    @Override
    public ByteArrayInputStream downloadTranscript(Long studentId) {
        return pdfService.generateTranscript(studentId);
    }

    @Override
    public void emailTranscript(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        ByteArrayInputStream pdf =
                pdfService.generateTranscript(studentId);

        String html = """
            <html>
            <body>

            <h2>Official Transcript</h2>

            <p>Dear <b>%s</b>,</p>

            <p>
            Please find your official transcript attached to this email.
            </p>

            <p>
            If you have any questions, please contact the administration.
            </p>

            <br>

            <p>
            Regards,<br>
            Student ERP Team
            </p>

            </body>
            </html>
            """.formatted(
                student.getUser().getFirstName()
        );

        emailService.sendEmailWithAttachment(
                student.getUser().getEmail(),
                "Official Transcript",
                html,
                pdf,
                "Transcript.pdf"
        );
    }

    private double getGradePoint(Grade grade) {

        return switch (grade) {
            case A_PLUS -> 4.0;
            case A -> 3.6;
            case B_PLUS -> 3.2;
            case B -> 2.8;
            case C -> 2.4;
            case D -> 2.0;
            case E -> 0.0;
        };
    }
}
