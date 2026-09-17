package com.campuspulse.campus_pulse.service;

import com.campuspulse.campus_pulse.dto.AssistantResponse;
import com.campuspulse.campus_pulse.model.Announcement;
import com.campuspulse.campus_pulse.model.CampusEvent;
import com.campuspulse.campus_pulse.model.Mentor;
import com.campuspulse.campus_pulse.model.Student;
import com.campuspulse.campus_pulse.routing.RoutingResult;
import com.campuspulse.campus_pulse.routing.SmartRoutingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentAssistantService {
    private final SmartRoutingService routingService;
    private final EventService eventService;
    private final AnnouncementService announcementService;
    private final MentorService mentorService;
    private final StudentService studentService;
    private final MessageService messageService;

    public AssistantResponse answer(String query) {
        RoutingResult routing = routingService.route(query);
        String normalized = query == null ? "" : query.toLowerCase();
        String response = switch (routing.destination()) {
            case "Events" -> eventResponse(normalized);
            case "Mentorship" -> mentorResponse(normalized);
            case "Announcements" -> announcementResponse(normalized);
            case "Students" -> studentResponse(normalized);
            case "Communication" -> "There are " + messageService.findAll().size() + " campus messages. Open Messages to continue a conversation.";
            case "Academic Office" -> "The Academic Office can help with exam timetables, attendance, grades, and course concerns. " + routing.suggestedAction();
            case "Administration" -> "Administration handles campus facilities, complaints, and service issues. " + routing.suggestedAction();
            default -> "I can route questions about events, academics, announcements, mentorship, and campus issues. " + routing.suggestedAction();
        };
        return new AssistantResponse(query, routing, response);
    }

    private String eventResponse(String query) {
        List<CampusEvent> events = eventService.findUpcoming(null);
        if (query.contains("technical") || query.contains("workshop")) {
            events = eventService.findByCategory("Technology");
        }
        if (query.contains("hackathon")) {
            events = events.stream().filter(event -> event.getTitle().toLowerCase().contains("hackathon")).toList();
        }
        return events.isEmpty() ? "There are no matching upcoming events in the campus calendar yet." : "I found " + events.size() + " matching upcoming event(s). Open Events to see dates, venues, and organizers.";
    }

    private String announcementResponse(String query) {
        List<Announcement> announcements = query.contains("high priority") || query.contains("important")
                ? announcementService.findImportant() : announcementService.findLatest();
        return announcements.isEmpty() ? "There are no high-priority announcements right now." : "I found " + announcements.size() + " important announcement(s). Open Announcements to read them.";
    }

    private String mentorResponse(String query) {
        String expertise = query.contains("data science") ? "data science"
                : query.contains("machine learning") ? "machine learning"
                : query.contains("cybersecurity") ? "cybersecurity"
                : query.contains("java") ? "java" : null;
        List<Mentor> mentors = expertise == null ? mentorService.findAvailable() : mentorService.findMatching(expertise);
        return mentors.isEmpty() ? "I could not find a matching mentor yet. Try another expertise keyword." : "I found " + mentors.size() + " possible mentor(s). Open Mentorship to review availability.";
    }

    private String studentResponse(String query) {
        if (query.contains("how many")) {
            return "Campus Pulse currently has " + studentService.count() + " students in the database.";
        }
        List<Student> students = studentService.findByInterest(query.contains("ai") ? "AI" : "data");
        return students.isEmpty() ? "I could not find students matching that interest." : "I found " + students.size() + " students with a matching interest.";
    }
}
