package com.ramesh.studenterp.serviceImpl;

import com.ramesh.studenterp.entity.User;
import com.ramesh.studenterp.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImp implements EmailService {

    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendHtmlEmail(String to, String subject, String htmlBody) {


        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message,true);


            helper.setFrom("raj168ramesh@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);

        }catch (Exception e){
            throw new RuntimeException("Failed to send email.", e);
        }



    }

    @Override
    public void sendWelcomeEmail(User user) {

        String html = """
        <html>
        <body>

        <h2>Welcome to Student ERP</h2>

        <p>Hello <b>%s</b>,</p>

        <p>Your account has been created successfully.</p>

        <p>You can now login using your registered email.</p>

        <br>

        <p>
        Regards,<br>
        Student ERP Team
        </p>

        </body>
        </html>
        """.formatted(user.getFirstName());

        sendHtmlEmail(
                user.getEmail(),
                "Welcome to Student ERP",
                html
        );

    }
}
