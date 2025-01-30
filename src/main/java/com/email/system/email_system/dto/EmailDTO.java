package com.email.system.email_system.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {
    private Long id;
    private String senderEmail;
    private String recipientEmail;
    private String subject;
    private String body;
    private boolean isRead;
    private LocalDateTime createdAt;
    private List<AttachmentDTO> attachments; // List of attachment DTOs
}
