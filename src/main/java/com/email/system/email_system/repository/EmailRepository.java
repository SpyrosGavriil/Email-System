package com.email.system.email_system.repository;

import com.email.system.email_system.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    // Find all emails sent by a given user (sender)
    List<Email> findBySenderId(Long senderId);

    // Find all emails received by a given user (recipient)
    List<Email> findByRecipientId(Long recipientId);

    // Find all emails by recipient and isDeleted = false (active inbox)
    List<Email> findByRecipientIdAndIsDeletedFalse(Long recipientId);

    // Find all unread emails for a given recipient
    List<Email> findByRecipientIdAndIsReadFalse(Long recipientId);

}
