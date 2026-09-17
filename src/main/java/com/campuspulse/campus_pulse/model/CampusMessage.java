package com.campuspulse.campus_pulse.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("messages")
public class CampusMessage {
    @Id
    private String id;

    @NotBlank
    private String sender;

    private String demoKey;

    @NotBlank
    private String receiver;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;

    @NotNull
    private LocalDateTime timestamp;

    @NotBlank
    private String status;
}
