package com.email.system.email_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "attachments")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "email_id", nullable = false)
    private Email email;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] fileData;
}
