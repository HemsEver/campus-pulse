package com.campuspulse.campus_pulse.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Document("students")
public class Student {
    @Id
    private String id;

    @NotBlank
    private String studentId;

    private String demoKey;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String department;

    @NotNull
    @Positive
    private Integer year;

    private List<String> interests;
    private String section;
    private String phone;
    private Double cgpa;
    private String status;
}
