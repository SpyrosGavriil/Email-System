package com.email.system.email_system.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentDTO {
    private Long id;
    private String fileName;
    private byte[] fileData;
}
