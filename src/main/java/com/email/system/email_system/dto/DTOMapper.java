package com.email.system.email_system.dto;

import com.email.system.email_system.model.Attachment;
import com.email.system.email_system.model.Email;
import com.email.system.email_system.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static EmailDTO toEmailDTO(Email email) {
        return EmailDTO.builder()
                .id(email.getId())
                .senderEmail(email.getSender().getEmail())
                .recipientEmail(email.getRecipient().getEmail())
                .subject(email.getSubject())
                .body(email.getBody())
                .isRead(email.isRead())
                .createdAt(email.getCreatedAt())
                .attachments(email.getAttachments().stream()
                        .map(DTOMapper::toAttachmentDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static AttachmentDTO toAttachmentDTO(Attachment attachment) {
        return AttachmentDTO.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .fileData(attachment.getFileData())
                .build();
    }
}

