package com.campuspulse.campus_pulse.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Document("events")
public class CampusEvent {
    @Id
    private String id;

    @NotBlank
    private String title;

    private String demoKey;

    @NotBlank
    private String description;

    @NotNull
    @FutureOrPresent
    private LocalDateTime eventDate;

    @NotBlank
    private String venue;

    @NotBlank
    private String organizer;

    @NotBlank
    private String category;

    @Builder.Default
    private List<String> registeredStudentIds = new ArrayList<>();
    private String registrationStatus;
    private Integer capacity;
}
