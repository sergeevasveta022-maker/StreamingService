package com.example.streaming_service.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReport(String toEmail, String csvContent, String xmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Film Report");
        helper.setText("Отчёт по фильмам во вложении");

        helper.addAttachment("films.csv", new ByteArrayResource(csvContent.getBytes(StandardCharsets.UTF_8)));
        helper.addAttachment("films.xml", new ByteArrayResource(xmlContent.getBytes(StandardCharsets.UTF_8)));

        mailSender.send(message);
    }
}
