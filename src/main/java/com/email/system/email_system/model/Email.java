package com.email.system.email_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String recipient;
    private String subject;

    @Lob
    private String content;

    private LocalDateTime sentAt;
    private String status; // e.g., "SENT", "FAILED"
}
