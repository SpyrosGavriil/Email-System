package com.email.system.email_system.service;

import com.email.system.email_system.model.Email;
import com.email.system.email_system.model.User;
import com.email.system.email_system.repository.EmailRepository;
import com.email.system.email_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserRepository userRepository;

    // Send an email
    public Email sendEmail(Long senderId, Long recipientId, String subject, String body) {
        Optional<User> senderOpt = userRepository.findById(senderId);
        Optional<User> recipientOpt = userRepository.findById(recipientId);

        if (senderOpt.isEmpty() || recipientOpt.isEmpty()) {
            throw new IllegalArgumentException("Sender or recipient not found");
        }

        Email email = Email.builder()
                .sender(senderOpt.get())
                .recipient(recipientOpt.get())
                .subject(subject)
                .body(body)
                .isRead(false)
                .isDeleted(false)
                .isArchived(false)
                .build();

        return emailRepository.save(email);
    }

    // Get all emails for a recipient
    public List<Email> getInbox(Long recipientId) {
        return emailRepository.findByRecipientIdAndIsDeletedFalse(recipientId);
    }

    // Mark an email as read
    public void markAsRead(Long emailId) {
        Optional<Email> emailOpt = emailRepository.findById(emailId);
        if (emailOpt.isPresent()) {
            Email email = emailOpt.get();
            email.setRead(true);
            emailRepository.save(email);
        }
    }

    // Delete an email
    public void deleteEmail(Long emailId) {
        Optional<Email> emailOpt = emailRepository.findById(emailId);
        if (emailOpt.isPresent()) {
            Email email = emailOpt.get();
            email.setDeleted(true);
            emailRepository.save(email);
        }
    }
}
