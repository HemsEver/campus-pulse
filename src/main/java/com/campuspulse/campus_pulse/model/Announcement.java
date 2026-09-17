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
@Document("announcements")
public class Announcement {
    @Id
    private String id;

    @NotBlank
    private String title;

    private String demoKey;

    @NotBlank
    private String description;

    @NotBlank
    private String category;

    @NotNull
    private LocalDateTime publishedAt;

    @NotBlank
    private String priority;

    private String department;
    private String author;
    private String status;
}
