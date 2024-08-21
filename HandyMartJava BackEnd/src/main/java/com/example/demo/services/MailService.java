package com.example.demo.services;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public CompletableFuture<Void> sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(message);
            log.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Exception while sending email: ", e);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> sendEmailWithAttachment(String to, String subject, String body, MultipartFile attachment) {
        try {
            log.info("Creating MIME message...");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            log.info("Setting email properties...");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            if (attachment != null && !attachment.isEmpty()) {
                log.info("Adding attachment: {}", attachment.getOriginalFilename());
                helper.addAttachment(attachment.getOriginalFilename(), attachment);
            } else {
                log.warn("No attachment provided or attachment is empty.");
            }

            log.info("Sending email...");
            javaMailSender.send(message);
            log.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Exception while sending email with attachment: ", e);
        }
        return CompletableFuture.completedFuture(null);
    }
    
    public CompletableFuture<Void> sendOtpEmail(String to, String otp) {
        String subject = "Your OTP for Password Reset";
        String body = "Your One-Time Password (OTP) for resetting your password is: " + otp + ". This OTP is valid for 10 minutes.";
        return sendEmail(to, subject, body);
    }
}
