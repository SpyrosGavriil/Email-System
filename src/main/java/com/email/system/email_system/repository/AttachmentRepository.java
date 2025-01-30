package com.email.system.email_system.repository;


import com.email.system.email_system.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    // Find all attachments for a given email
    List<Attachment> findByEmailId(Long emailId);

}

