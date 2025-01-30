package com.email.system.email_system.controller;

import com.email.system.email_system.model.Attachment;
import com.email.system.email_system.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    // Upload an attachment
    @PostMapping("/upload")
    public Attachment uploadAttachment(@RequestParam Long emailId,
            @RequestParam String fileName,
            @RequestParam byte[] fileData) {
        return attachmentService.uploadAttachment(emailId, fileName, fileData);
    }

    // Get attachments for an email
    @GetMapping("/{emailId}")
    public List<Attachment> getAttachments(@PathVariable Long emailId) {
        return attachmentService.getAttachments(emailId);
    }
}
