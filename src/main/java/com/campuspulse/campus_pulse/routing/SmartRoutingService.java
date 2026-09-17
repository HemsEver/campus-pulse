package com.campuspulse.campus_pulse.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class SmartRoutingService {
    public RoutingResult route(String query) {
        if (query == null || query.isBlank()) {
            return new RoutingResult("General Help Desk", "Please enter a question so the hub can identify the right campus service.", "Describe the issue in one sentence.", List.of("help", "routing"));
        }

        String normalized = query.toLowerCase(Locale.ROOT);
        if (containsAny(normalized, "mentor", "mentoring", "senior", "guidance", "career", "java")) {
            return new RoutingResult("Mentorship", "The query suggests that peer or technical guidance would help.", "Search available mentors by expertise.", List.of("mentorship", "java", "advising"));
        }
        if (containsAny(normalized, "student", "students", "cse count", "department count")) {
            return new RoutingResult("Students", "The query asks about the campus student directory or student numbers.", "Open Students to search the student directory.", List.of("students", "directory", "search"));
        }
        if (containsAny(normalized, "message", "messages", "communication", "inbox")) {
            return new RoutingResult("Communication", "The query asks about a campus conversation or message.", "Open Messages to continue the conversation.", List.of("messages", "inbox", "connect"));
        }
        if (containsAny(normalized, "event", "club", "workshop", "fest", "join", "placement")) {
            return new RoutingResult("Events", "The query mentions campus activities or participation.", "Browse upcoming events and contact the organizer.", List.of("events", "calendar", "clubs"));
        }
        if (containsAny(normalized, "exam", "timetable", "course", "academic", "grade", "attendance")) {
            return new RoutingResult("Academic Office", "The query relates to academic records, schedules, or coursework.", "Contact the Academic Office with your student ID.", List.of("academics", "schedule", "records"));
        }
        if (containsAny(normalized, "issue", "complaint", "report", "facility", "maintenance", "campus")) {
            return new RoutingResult("Administration", "The query describes a campus service issue or administrative request.", "Create a message for the Administration team.", List.of("support", "admin", "service"));
        }
        if (containsAny(normalized, "announcement", "notice", "deadline", "important")) {
            return new RoutingResult("Announcements", "The query asks about campus notices or deadlines.", "Review important announcements.", List.of("announcements", "alerts", "deadlines"));
        }
        return new RoutingResult("General Help Desk", "No specific category matched, so the query is best handled by the general help desk.", "Send a message with more details.", List.of("general", "fallback"));
    }

    private boolean containsAny(String query, String... keywords) {
        for (String keyword : keywords) {
            if (query.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
