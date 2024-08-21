package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.MailService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public CompletableFuture<ResponseEntity<String>> sendMail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {
        return mailService.sendEmail(to, subject, body)
                .thenApplyAsync(v -> ResponseEntity.ok("Email sent successfully"));
    }

    @PostMapping("/sendWithAttachment")
    public CompletableFuture<ResponseEntity<String>> sendMailWithAttachment(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam MultipartFile attachment) {
        return mailService.sendEmailWithAttachment(to, subject, body, attachment)
                .thenApplyAsync(v -> ResponseEntity.ok("Email with attachment sent successfully"));
    }
    
}
