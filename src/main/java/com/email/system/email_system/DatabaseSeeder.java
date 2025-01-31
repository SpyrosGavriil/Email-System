package com.email.system.email_system;

import com.email.system.email_system.model.Attachment;
import com.email.system.email_system.model.Email;
import com.email.system.email_system.model.User;
import com.email.system.email_system.repository.AttachmentRepository;
import com.email.system.email_system.repository.EmailRepository;
import com.email.system.email_system.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder {

        private final UserRepository userRepository;
        private final EmailRepository emailRepository;
        private final AttachmentRepository attachmentRepository;
        private final BCryptPasswordEncoder passwordEncoder;

        @PostConstruct
        public void seedDatabase() {
                if (userRepository.count() > 0) {
                        return; // Prevent seeding if data already exists
                }

                // Create Users
                User user1 = User.builder()
                                .email("user1@example.com")
                                .password(passwordEncoder.encode("password123"))
                                .createdAt(LocalDateTime.now())
                                .build();

                User user2 = User.builder()
                                .email("user2@example.com")
                                .password(passwordEncoder.encode("password123"))
                                .createdAt(LocalDateTime.now())
                                .build();

                userRepository.saveAll(List.of(user1, user2));

                // Create Emails
                Email email1 = Email.builder()
                                .sender(user1)
                                .recipient(user2)
                                .subject("Hello User2")
                                .body("This is a test email from User1 to User2.")
                                .isRead(false)
                                .isDeleted(false)
                                .isArchived(false)
                                .createdAt(LocalDateTime.now())
                                .build();

                Email email2 = Email.builder()
                                .sender(user2)
                                .recipient(user1)
                                .subject("Re: Hello")
                                .body("Thanks for your email, User1!")
                                .isRead(false)
                                .isDeleted(false)
                                .isArchived(false)
                                .createdAt(LocalDateTime.now())
                                .build();

                emailRepository.saveAll(List.of(email1, email2));

                // Create Attachments
                Attachment attachment1 = Attachment.builder()
                                .email(email1)
                                .fileName("example.txt")
                                .fileData("Hello, this is a sample file.".getBytes(StandardCharsets.UTF_8))
                                .build();

                Attachment attachment2 = Attachment.builder()
                                .email(email2)
                                .fileName("response.txt")
                                .fileData("Response file content.".getBytes(StandardCharsets.UTF_8))
                                .build();

                attachmentRepository.saveAll(List.of(attachment1, attachment2));
        }
}
