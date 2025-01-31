package com.email.system.email_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailRequest {
    private Long senderId;
    private Long recipientId;
    private String subject;
    private String body;
}

