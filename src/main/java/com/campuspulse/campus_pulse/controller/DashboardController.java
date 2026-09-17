package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.service.AnnouncementService;
import com.campuspulse.campus_pulse.service.EventService;
import com.campuspulse.campus_pulse.service.MentorService;
import com.campuspulse.campus_pulse.service.MessageService;
import com.campuspulse.campus_pulse.service.StudentAssistantService;
import com.campuspulse.campus_pulse.service.StudentService;
import com.campuspulse.campus_pulse.routing.SmartRoutingService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final EventService eventService;
    private final AnnouncementService announcementService;
    private final MentorService mentorService;
    private final MessageService messageService;
    private final SmartRoutingService routingService;
    private final StudentAssistantService assistantService;
    private final StudentService studentService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("upcomingEvents", eventService.findUpcoming(null));
        model.addAttribute("importantAnnouncements", announcementService.findImportant());
        model.addAttribute("availableMentors", mentorService.findAvailable());
        model.addAttribute("pendingMessages", messageService.countPending());
        model.addAttribute("studentCount", studentService.count());
        model.addAttribute("mentorCount", mentorService.count());
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        int hour = LocalDateTime.now().getHour();
        if (hour < 12) {
            model.addAttribute("greetingText", "Good morning, campus.");
        } else if (hour < 18) {
            model.addAttribute("greetingText", "Good afternoon, campus.");
        } else {
            model.addAttribute("greetingText", "Good evening, campus.");
        }
        return "dashboard";
    }

    @GetMapping("/students")
    public String studentsPage(Model model) {
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        model.addAttribute("module", "Students");
        return "students";
    }

    @GetMapping("/routing")
    public String routingPage(Model model) {
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        return "routing-ui";
    }

    @PostMapping("/routing")
    public String route(@RequestParam String query, Model model) {
        model.addAttribute("query", query);
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        model.addAttribute("routing", routingService.route(query));
        return "routing-ui";
    }

    @GetMapping("/assistant")
    public String assistantPage(Model model) {
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        return "assistant";
    }

    @GetMapping("/events")
    public String eventsPage(Model model) {
        model.addAttribute("module", "Events");
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        return "events";
    }

    @GetMapping("/announcements")
    public String announcementsPage(Model model) {
        model.addAttribute("module", "Announcements");
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        return "announcements";
    }

    @GetMapping("/mentorship")
    public String mentorshipPage(Model model) {
        model.addAttribute("module", "Mentorship");
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        return "mentorship";
    }

    @GetMapping("/messages")
    public String messagesPage(Model model) {
        model.addAttribute("module", "Messages");
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        return "messages";
    }

    @PostMapping("/assistant")
    public String assistant(@RequestParam String query, Model model) {
        model.addAttribute("query", query);
        model.addAttribute("todayLabel", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US)));
        model.addAttribute("answer", assistantService.answer(query));
        return "assistant";
    }
}
