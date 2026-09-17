package com.campuspulse.campus_pulse.dto;

import com.campuspulse.campus_pulse.routing.RoutingResult;

public record AssistantResponse(String query, RoutingResult routing, String response) {
}
