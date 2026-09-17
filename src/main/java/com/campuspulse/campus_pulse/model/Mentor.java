package com.campuspulse.campus_pulse.model;

import jakarta.validation.constraints.NotBlank;
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
@Document("mentors")
public class Mentor {
    @Id
    private String id;

    @NotBlank
    private String name;

    private String demoKey;
    private String facultyId;
    private String email;

    @NotBlank
    private String department;

    @NotBlank
    private String expertise;

    @NotBlank
    private String availability;

    @NotBlank
    private String requestStatus;

    @Builder.Default
    private List<String> bookedStudentIds = new ArrayList<>();
    private List<String> mentoringAreas;
    private Integer experienceYears;
}
