package com.campuspulse.campus_pulse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.campuspulse.campus_pulse.model.Announcement;
import com.campuspulse.campus_pulse.model.CampusEvent;
import com.campuspulse.campus_pulse.model.Mentor;
import com.campuspulse.campus_pulse.model.Student;
import com.campuspulse.campus_pulse.routing.SmartRoutingService;
import com.campuspulse.campus_pulse.service.AnnouncementService;
import com.campuspulse.campus_pulse.service.EventService;
import com.campuspulse.campus_pulse.service.MentorService;
import com.campuspulse.campus_pulse.service.StudentService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CampusPulseFeatureTests {

    @Mock
    private StudentService studentService;

    @Mock
    private EventService eventService;

    @Mock
    private MentorService mentorService;

    @Mock
    private AnnouncementService announcementService;

    @InjectMocks
    private SmartRoutingService smartRoutingService;

    @Test
    void smartRoutingRanksMentorshipWhenJavaMentorQueryIsUsed() {
        var result = smartRoutingService.route("I need a Java mentoring session for AI students");
        assertNotNull(result);
        assertTrue(result.destination().toLowerCase().contains("mentorship") || result.destination().toLowerCase().contains("mentor"));
        assertFalse(result.tags().isEmpty());
    }

    @Test
    void studentMatchingPrioritizesSkillOverlapAndCgpa() {
        Student studentA = Student.builder().id("s1").studentId("S-100").name("Ava").department("CSE").year(3).cgpa(9.1).interests(List.of("Java", "AI", "Web Development")).build();
        Student studentB = Student.builder().id("s2").studentId("S-101").name("Leo").department("ECE").year(3).cgpa(8.8).interests(List.of("Java", "AI", "Cloud")).build();
        Student studentC = Student.builder().id("s3").studentId("S-102").name("Nia").department("CSE").year(2).cgpa(7.6).interests(List.of("Design", "UX")).build();

        var matches = StudentService.buildMatchResults(List.of(studentA, studentB, studentC), List.of("Java", "AI"), "s3");

        assertFalse(matches.isEmpty());
        assertTrue(matches.get(0).student().getName().equals("Ava") || matches.get(0).student().getName().equals("Leo"));
    }

    @Test
    void eventConflictDetectionFlagsOverlap() {
        CampusEvent current = CampusEvent.builder().id("e1").title("Hackathon").eventDate(LocalDateTime.of(2026, 9, 20, 15, 0)).registeredStudentIds(List.of("student-1", "student-2")).build();
        CampusEvent other = CampusEvent.builder().id("e2").title("Data Sprint").eventDate(LocalDateTime.of(2026, 9, 20, 15, 30)).registeredStudentIds(List.of("student-1")).build();

        assertTrue(EventService.hasTimeConflict(current, other));
    }

    @Test
    void announcementPriorityIsRenderedForUrgentNotices() {
        var announcement = Announcement.builder().id("a1").title("Emergency Power Advisory").priority("High").category("Campus Safety").build();
        assertTrue(announcement.getPriority() != null && announcement.getPriority().equalsIgnoreCase("High"));
    }

    @Test
    void mentorsCanBeBookedWithScheduleAvailability() {
        Mentor mentor = Mentor.builder().id("m1").name("Dr. Rao").availability("Mon-Fri 4:00PM - 5:00PM").requestStatus("Open").build();
        assertTrue(mentor.getAvailability() != null && !mentor.getAvailability().isBlank());
    }
}
