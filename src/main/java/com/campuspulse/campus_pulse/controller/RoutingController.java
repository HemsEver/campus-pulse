package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.routing.RoutingResult;
import com.campuspulse.campus_pulse.routing.SmartRoutingService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routing")
@RequiredArgsConstructor
@Validated
public class RoutingController {
    private final SmartRoutingService service;

    @GetMapping
    public RoutingResult route(@RequestParam @NotBlank String query) {
        return service.route(query);
    }

    @GetMapping("/search")
    public RoutingResult search(@RequestParam @NotBlank String query) {
        return service.route(query);
    }
}
