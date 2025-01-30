package com.email.system.email_system.controller;

import com.email.system.email_system.model.Email;
import com.email.system.email_system.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Send an email
    @PostMapping("/send")
    public Email sendEmail(@RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam String subject,
            @RequestParam String body) {
        return emailService.sendEmail(senderId, recipientId, subject, body);
    }

    // Get inbox for a user
    @GetMapping("/inbox/{userId}")
    public List<Email> getInbox(@PathVariable Long userId) {
        return emailService.getInbox(userId);
    }

    // Mark an email as read
    @PostMapping("/{emailId}/read")
    public void markAsRead(@PathVariable Long emailId) {
        emailService.markAsRead(emailId);
    }

    // Delete an email
    @DeleteMapping("/{emailId}")
    public void deleteEmail(@PathVariable Long emailId) {
        emailService.deleteEmail(emailId);
    }
}
