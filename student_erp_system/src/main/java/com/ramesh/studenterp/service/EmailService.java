package com.ramesh.studenterp.service;

import com.ramesh.studenterp.entity.User;

import java.io.ByteArrayInputStream;

public interface EmailService {

    void sendHtmlEmail(String to, String subject, String htmlBody);

    void sendWelcomeEmail(User user);

    void sendEmailWithAttachment(
            String to,
            String subject,
            String html,
            ByteArrayInputStream pdf,
            String fileName
    );
}
