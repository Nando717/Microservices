package com.microservice.ms_Email.dtos;

import java.util.UUID;

public record EmailRecordDto(UUID userID,
                             String emailTo,
                             String subject,
                             String text) {
}
