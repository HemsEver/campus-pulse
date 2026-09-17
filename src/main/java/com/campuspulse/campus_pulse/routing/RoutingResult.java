package com.campuspulse.campus_pulse.routing;

import java.util.List;

public record RoutingResult(String destination, String explanation, String suggestedAction, List<String> tags) {
    public RoutingResult(String destination, String explanation, String suggestedAction) {
        this(destination, explanation, suggestedAction, List.of());
    }
}
