package com.ramesh.studenterp.serviceImpl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ramesh.studenterp.entity.Mark;
import com.ramesh.studenterp.entity.Student;
import com.ramesh.studenterp.enums.Grade;
import com.ramesh.studenterp.exception.ResourceNotFoundException;
import com.ramesh.studenterp.repository.MarkRepository;
import com.ramesh.studenterp.repository.StudentRepository;
import com.ramesh.studenterp.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;

    @Override
    public ByteArrayInputStream generateTranscript(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found."));

        List<Mark> marks = markRepository.findByEnrollmentStudentId(studentId);

        double overallGpa = marks.stream()
                .mapToDouble(mark -> getGradePoint(mark.getGrade()))
                .average()
                .orElse(0.0);

        ByteArrayOutputStream out  = new ByteArrayOutputStream();

        Document document = new Document();
        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    20
            );

            Font subTitleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    14
            );

            Font normalFont = FontFactory.getFont(
                    FontFactory.HELVETICA,
                    12
            );

            Font boldFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    12
            );

            Paragraph title = new Paragraph(
                    "STUDENT ERP SYSTEM",
                    titleFont
            );

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

            Paragraph subtitle = new Paragraph(
                    "OFFICIAL TRANSCRIPT",
                    subTitleFont
            );

            subtitle.setAlignment(Element.ALIGN_CENTER);

            document.add(subtitle);

            document.add(new Paragraph(" "));

            String today = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

            PdfPTable infoTable = new PdfPTable(2);

            infoTable.setWidths(new float[]{1, 2});

            infoTable.setSpacingBefore(10);

            infoTable.setSpacingAfter(15);

            infoTable.addCell(createInfoCell("Student Name"));
            infoTable.addCell(createInfoCell(
                    student.getUser().getFirstName()
                            + " "
                            + student.getUser().getLastName()));

            infoTable.addCell(createInfoCell("Roll Number"));
            infoTable.addCell(createInfoCell(
                    student.getRollNumber().toString()));

            infoTable.addCell(createInfoCell("Admission Number"));
            infoTable.addCell(createInfoCell(
                    student.getAdmissionNumber()));

            infoTable.addCell(createInfoCell("Generated On"));
            infoTable.addCell(createInfoCell(today));

            document.add(infoTable);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);

            table.addCell(createHeaderCell("Subject"));
            table.addCell(createHeaderCell("Exam"));
            table.addCell(createHeaderCell("Marks"));
            table.addCell(createHeaderCell("Grade"));
            table.addCell(createHeaderCell("GPA"));

            for (Mark mark : marks) {

                table.addCell(mark.getEnrollment()
                        .getSubject()
                        .getSubjectName());

                table.addCell(createBodyCell(mark.getExamType().name()));

                table.addCell(
                        createBodyCell(
                                mark.getObtainedMarks()
                                        + " / "
                                        + mark.getTotalMarks()
                        )
                );

                table.addCell(
                        createBodyCell(
                                formatGrade(mark.getGrade())
                        )
                );

                table.addCell(
                        createBodyCell(
                                String.format("%.1f",
                                        getGradePoint(mark.getGrade()))
                        )
                );
            }

            document.add(table);

            document.add(new Paragraph(" "));

            Paragraph gpa = new Paragraph(
                    "Overall GPA : " + String.format("%.2f", overallGpa),
                    boldFont
            );
            gpa.setAlignment(Element.ALIGN_RIGHT);
            document.add(gpa);


//            document.add(new Paragraph(" "));
//            String today = LocalDate.now()
//                    .format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

            Paragraph date = new Paragraph(
                    "Generated On : " + today,
                    normalFont
            );


            date.setAlignment(Element.ALIGN_RIGHT);

            document.add(date);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph signature = new Paragraph(
                    "________________________",
                    normalFont
            );

            signature.setAlignment(Element.ALIGN_RIGHT);

            document.add(signature);

            Paragraph signText = new Paragraph(
                    "Authorized Signature",
                    boldFont
            );
            signText.setAlignment(Element.ALIGN_RIGHT);

            document.add(signText);

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error generating transcript PDF",
                    e
            );
        }


        return new ByteArrayInputStream(out.toByteArray());
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
            default -> 0.0;
        };
    }

    private String formatGrade(Grade grade) {

        return switch (grade) {
            case A_PLUS -> "A+";
            case B_PLUS -> "B+";
            case A -> "A";
            case B -> "B";
            case C -> "C";
            case D -> "D";
            case E -> "E";
            default -> grade.name();
        };
    }

    private PdfPCell createHeaderCell(String text) {

        Font headerFont = FontFactory.getFont(
                FontFactory.HELVETICA_BOLD,
                12
        );

        PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setPadding(8);

        cell.setBackgroundColor(java.awt.Color.LIGHT_GRAY);

        return cell;
    }

    private PdfPCell createInfoCell(String text) {

        Font font = FontFactory.getFont(
                FontFactory.HELVETICA,
                11
        );

        PdfPCell cell = new PdfPCell(new Phrase(text, font));

        cell.setBorder(PdfPCell.NO_BORDER);

        cell.setPadding(5);

        return cell;
    }

    private PdfPCell createBodyCell(String text) {

        Font font = FontFactory.getFont(
                FontFactory.HELVETICA,
                11
        );

        PdfPCell cell = new PdfPCell(new Phrase(text, font));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setPadding(6);

        return cell;
    }
}
