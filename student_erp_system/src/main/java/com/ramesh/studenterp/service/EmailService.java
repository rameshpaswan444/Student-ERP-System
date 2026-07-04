package com.ramesh.studenterp.service;

import com.ramesh.studenterp.entity.User;

public interface EmailService {

    void sendHtmlEmail(String to, String subject, String htmlBody);

    void sendWelcomeEmail(User user);
}
