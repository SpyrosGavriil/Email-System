package com.email.system.email_system.service;

import com.email.system.email_system.model.Attachment;
import com.email.system.email_system.model.Email;
import com.email.system.email_system.repository.AttachmentRepository;
import com.email.system.email_system.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private EmailRepository emailRepository;

    // Upload an attachment for an email
    public Attachment uploadAttachment(Long emailId, String fileName, byte[] fileData) {
        Optional<Email> emailOpt = emailRepository.findById(emailId);
        if (emailOpt.isEmpty()) {
            throw new IllegalArgumentException("Email not found");
        }

        Attachment attachment = Attachment.builder()
                .email(emailOpt.get())
                .fileName(fileName)
                .fileData(fileData)
                .build();

        return attachmentRepository.save(attachment);
    }

    // Get attachments for an email
    public List<Attachment> getAttachments(Long emailId) {
        return attachmentRepository.findByEmailId(emailId);
    }
}
