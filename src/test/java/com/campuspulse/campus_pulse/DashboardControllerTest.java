package com.campuspulse.campus_pulse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.campuspulse.campus_pulse.controller.DashboardController;
import com.campuspulse.campus_pulse.routing.SmartRoutingService;
import com.campuspulse.campus_pulse.service.AnnouncementService;
import com.campuspulse.campus_pulse.service.EventService;
import com.campuspulse.campus_pulse.service.MentorService;
import com.campuspulse.campus_pulse.service.MessageService;
import com.campuspulse.campus_pulse.service.StudentAssistantService;
import com.campuspulse.campus_pulse.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

class DashboardControllerTest {

    @Test
    void dashboardIncludesDynamicDateLabel() {
        EventService eventService = mock(EventService.class);
        AnnouncementService announcementService = mock(AnnouncementService.class);
        MentorService mentorService = mock(MentorService.class);
        MessageService messageService = mock(MessageService.class);
        SmartRoutingService routingService = mock(SmartRoutingService.class);
        StudentAssistantService assistantService = mock(StudentAssistantService.class);
        StudentService studentService = mock(StudentService.class);

        when(eventService.findUpcoming(null)).thenReturn(java.util.Collections.emptyList());
        when(announcementService.findImportant()).thenReturn(java.util.Collections.emptyList());
        when(mentorService.findAvailable()).thenReturn(java.util.Collections.emptyList());
        when(messageService.countPending()).thenReturn(0L);
        when(studentService.count()).thenReturn(0L);
        when(mentorService.count()).thenReturn(0L);

        DashboardController dashboardController = new DashboardController(
            eventService,
            announcementService,
            mentorService,
            messageService,
            routingService,
            assistantService,
            studentService
        );

        Model model = new ExtendedModelMap();
        String view = dashboardController.dashboard(model);

        assertEquals("dashboard", view);
        assertNotNull(model.getAttribute("todayLabel"));
        assertNotNull(model.getAttribute("greetingText"));
    }

    @Test
    void studentsPageRendersStudentDirectoryView() {
        DashboardController dashboardController = new DashboardController(
            mock(EventService.class),
            mock(AnnouncementService.class),
            mock(MentorService.class),
            mock(MessageService.class),
            mock(SmartRoutingService.class),
            mock(StudentAssistantService.class),
            mock(StudentService.class)
        );

        Model model = new ExtendedModelMap();
        String view = dashboardController.studentsPage(model);

        assertEquals("students", view);
        assertEquals("Students", model.getAttribute("module"));
    }
}
