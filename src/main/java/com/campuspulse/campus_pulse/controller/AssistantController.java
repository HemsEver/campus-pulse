package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.dto.AssistantResponse;
import com.campuspulse.campus_pulse.service.StudentAssistantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
public class AssistantController {
    private final StudentAssistantService service;

    @PostMapping
    public AssistantResponse answer(@Valid @RequestBody AssistantRequest request) {
        return service.answer(request.query());
    }

    public record AssistantRequest(@NotBlank String query) { }
}
