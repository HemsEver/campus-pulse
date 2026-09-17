package com.campuspulse.campus_pulse.routing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SmartRoutingServiceTests {
    private final SmartRoutingService service = new SmartRoutingService();

    @Test
    void routesExamQuestionsToAcademicOffice() {
        RoutingResult result = service.route("I have a problem with my exam timetable");

        assertThat(result.destination()).isEqualTo("Academic Office");
    }

    @Test
    void routesCodingEventQuestionsToEvents() {
        RoutingResult result = service.route("I want to join a coding event");

        assertThat(result.destination()).isEqualTo("Events");
    }

    @Test
    void routesJavaQuestionsToMentorship() {
        RoutingResult result = service.route("Find a mentor for Java");

        assertThat(result.destination()).isEqualTo("Mentorship");
    }

    @Test
    void givesGeneralHelpForUnknownQuestions() {
        RoutingResult result = service.route("Where do I start?");

        assertThat(result.destination()).isEqualTo("General Help Desk");
    }
}
