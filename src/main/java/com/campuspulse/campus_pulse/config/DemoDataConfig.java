package com.campuspulse.campus_pulse.config;

import com.campuspulse.campus_pulse.model.Announcement;
import com.campuspulse.campus_pulse.model.CampusEvent;
import com.campuspulse.campus_pulse.model.CampusMessage;
import com.campuspulse.campus_pulse.model.Mentor;
import com.campuspulse.campus_pulse.model.Student;
import com.campuspulse.campus_pulse.repository.AnnouncementRepository;
import com.campuspulse.campus_pulse.repository.CampusEventRepository;
import com.campuspulse.campus_pulse.repository.CampusMessageRepository;
import com.campuspulse.campus_pulse.repository.MentorRepository;
import com.campuspulse.campus_pulse.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DemoDataConfig {
    @Bean
    @ConditionalOnProperty(name = "campus-pulse.demo-data", havingValue = "true")
    CommandLineRunner loadDemoData(StudentRepository students, MentorRepository mentors, CampusEventRepository events,
            AnnouncementRepository announcements, CampusMessageRepository messages) {
        return args -> {
            seedStudents(students);
            seedMentors(mentors);
            seedEvents(events);
            seedAnnouncements(announcements);
            seedMessages(messages);
        };
    }

    private void seedStudents(StudentRepository repository) {
        List<Student> records = List.of(
                student("student-001", "Ananya Sharma", "CSE2026-001", "ananya.sharma@campuspulse.edu", "Computer Science and Engineering", 3, "A", 8.7, "Java", "Artificial Intelligence"),
                student("student-002", "Arjun Kumar", "CSE2026-002", "arjun.kumar@campuspulse.edu", "Computer Science and Engineering", 2, "B", 8.3, "Machine Learning", "Hackathons"),
                student("student-003", "Priya Nair", "IT2026-003", "priya.nair@campuspulse.edu", "Information Technology", 4, "A", 9.1, "Data Science", "Cloud Computing"),
                student("student-004", "Rahul Krishnan", "CSE2026-004", "rahul.krishnan@campuspulse.edu", "Computer Science and Engineering", 3, "B", 7.9, "Cybersecurity", "Java"),
                student("student-005", "Kavya S", "AIDS2026-005", "kavya.s@campuspulse.edu", "Artificial Intelligence and Data Science", 2, "A", 8.8, "Artificial Intelligence", "Python"),
                student("student-006", "Aditya Raj", "ECE2026-006", "aditya.raj@campuspulse.edu", "Electronics and Communication Engineering", 4, "B", 8.0, "IoT", "Robotics"),
                student("student-007", "Meera Iyer", "IT2026-007", "meera.iyer@campuspulse.edu", "Information Technology", 3, "A", 8.9, "Web Development", "Open Source"),
                student("student-008", "Vishnu Prasad", "EEE2026-008", "vishnu.prasad@campuspulse.edu", "Electrical and Electronics Engineering", 2, "B", 7.6, "Embedded Systems", "Sports"),
                student("student-009", "Harini M", "AIDS2026-009", "harini.m@campuspulse.edu", "Artificial Intelligence and Data Science", 4, "A", 9.3, "Data Science", "Research"),
                student("student-010", "Sanjay Kumar", "CSE2026-010", "sanjay.kumar@campuspulse.edu", "Computer Science and Engineering", 1, "A", 8.1, "Competitive Programming", "Java"),
                student("student-011", "Nithya R", "ECE2026-011", "nithya.r@campuspulse.edu", "Electronics and Communication Engineering", 3, "B", 8.4, "VLSI", "Artificial Intelligence"),
                student("student-012", "Rohit Sharma", "IT2026-012", "rohit.sharma@campuspulse.edu", "Information Technology", 4, "A", 7.8, "Cloud Computing", "DevOps"),
                student("student-013", "Aishwarya K", "EEE2026-013", "aishwarya.k@campuspulse.edu", "Electrical and Electronics Engineering", 2, "A", 8.6, "Renewable Energy", "Photography"),
                student("student-014", "Karthik S", "CSE2026-014", "karthik.s@campuspulse.edu", "Computer Science and Engineering", 4, "B", 8.5, "Software Engineering", "Java"),
                student("student-015", "Divya Prakash", "AIDS2026-015", "divya.prakash@campuspulse.edu", "Artificial Intelligence and Data Science", 1, "A", 8.2, "Machine Learning", "Design"));
        records.stream().filter(record -> !repository.existsByDemoKey(record.getDemoKey())).forEach(repository::save);
    }

    private Student student(String key, String name, String studentId, String email, String department, int year,
            String section, double cgpa, String... interests) {
        return Student.builder().demoKey(key).studentId(studentId).name(name).email(email).department(department)
                .year(year).section(section).cgpa(cgpa).phone("987650" + key.substring(key.length() - 3))
                .status("Active").interests(List.of(interests)).build();
    }

    private void seedMentors(MentorRepository repository) {
        List<Mentor> records = List.of(
                mentor("mentor-001", "Dr. Radhika Menon", "FAC-CSE-01", "radhika.menon@campuspulse.edu", "Computer Science and Engineering", "Data Science, Machine Learning", "Available", 12, "Data Science", "Machine Learning"),
                mentor("mentor-002", "Prof. Arvind Rao", "FAC-CSE-02", "arvind.rao@campuspulse.edu", "Computer Science and Engineering", "Java, Software Engineering", "Available", 10, "Java", "Software Engineering"),
                mentor("mentor-003", "Dr. Neha Kapoor", "FAC-IT-01", "neha.kapoor@campuspulse.edu", "Information Technology", "Cybersecurity, Networks", "Available", 9, "Cybersecurity", "Networks"),
                mentor("mentor-004", "Prof. Suresh Babu", "FAC-IT-02", "suresh.babu@campuspulse.edu", "Information Technology", "Cloud Computing, Databases", "Available", 14, "Cloud Computing", "Database Systems"),
                mentor("mentor-005", "Dr. Lakshmi Iyer", "FAC-AI-01", "lakshmi.iyer@campuspulse.edu", "Artificial Intelligence and Data Science", "Artificial Intelligence, Deep Learning", "Available", 11, "Artificial Intelligence", "Deep Learning"),
                mentor("mentor-006", "Prof. Vivek Nair", "FAC-ECE-01", "vivek.nair@campuspulse.edu", "Electronics and Communication Engineering", "IoT, Embedded Systems", "By Request", 8, "IoT", "Embedded Systems"),
                mentor("mentor-007", "Dr. Pooja Deshmukh", "FAC-CSE-03", "pooja.deshmukh@campuspulse.edu", "Computer Science and Engineering", "Web Development, Open Source", "Available", 7, "Web Development", "Open Source"),
                mentor("mentor-008", "Prof. Mohan Joseph", "FAC-EEE-01", "mohan.joseph@campuspulse.edu", "Electrical and Electronics Engineering", "Competitive Programming, Algorithms", "Available", 15, "Competitive Programming", "Algorithms"));
        records.stream().filter(record -> !repository.existsByDemoKey(record.getDemoKey())).forEach(repository::save);
    }

    private Mentor mentor(String key, String name, String facultyId, String email, String department, String expertise,
            String availability, int experience, String... areas) {
        return Mentor.builder().demoKey(key).name(name).facultyId(facultyId).email(email).department(department)
                .expertise(expertise).availability(availability).requestStatus("Open").experienceYears(experience)
                .mentoringAreas(List.of(areas)).build();
    }

    private void seedEvents(CampusEventRepository repository) {
        LocalDateTime now = LocalDateTime.now();
        List<CampusEvent> records = List.of(
                event("event-001", "AI & Machine Learning Workshop", "Hands-on model building with faculty mentors.", 2, "AI Lab", "AI Club", "Technology"),
                event("event-002", "Campus Hackathon 2026", "Build a solution for a real campus challenge in 24 hours.", 5, "Innovation Hub", "Developer Club", "Technology"),
                event("event-003", "Java Coding Challenge", "A friendly algorithms and Java problem-solving contest.", 8, "Programming Lab 2", "CodeChef Campus Chapter", "Technology"),
                event("event-004", "Data Science Career Seminar", "Industry speakers discuss portfolios and early careers.", 11, "Seminar Hall", "Placement Cell", "Career"),
                event("event-005", "Cybersecurity Awareness Workshop", "Learn practical digital safety habits for students.", 14, "CSE Auditorium", "CyberSec Club", "Technology"),
                event("event-006", "Inter-Department Sports Meet", "Track, volleyball, and cricket across campus teams.", 18, "University Ground", "Sports Committee", "Sports"),
                event("event-007", "Innovation & Startup Meetup", "Meet student founders and prototype reviewers.", 22, "Entrepreneurship Cell", "E-Cell", "Community"),
                event("event-008", "Placement Preparation Bootcamp", "Resume review, aptitude practice, and mock interviews.", 26, "Career Development Centre", "Placement Cell", "Career"),
                event("event-009", "Aarohan Cultural Fest", "Music, theatre, dance, and student showcases.", 31, "Open Air Theatre", "Cultural Committee", "Cultural"),
                event("event-010", "Git & Open Source Workshop", "Make your first contribution to a collaborative project.", 35, "Digital Library", "Open Source Club", "Technology"));
        records.stream().filter(record -> !repository.existsByDemoKey(record.getDemoKey())).forEach(repository::save);
    }

    private CampusEvent event(String key, String title, String description, int days, String venue, String organizer, String category) {
        return CampusEvent.builder().demoKey(key).title(title).description(description).eventDate(LocalDateTime.now().plusDays(days))
                .venue(venue).organizer(organizer).category(category).registrationStatus("Open").capacity(80).build();
    }

    private void seedAnnouncements(AnnouncementRepository repository) {
        LocalDateTime now = LocalDateTime.now();
        List<Announcement> records = List.of(
                announcement("announcement-001", "Internal assessment schedule released", "The internal assessment timetable is available on the Academic Office notice board.", "Academic", now.minusDays(1), "HIGH", "Academic Office"),
                announcement("announcement-002", "Placement registration opened", "Eligible final-year students can register for the next recruitment drive.", "Placement", now.minusDays(2), "HIGH", "Placement Cell"),
                announcement("announcement-003", "Hackathon registrations started", "Register your team for Campus Hackathon 2026 before Friday.", "Events", now.minusDays(3), "MEDIUM", "Developer Club"),
                announcement("announcement-004", "Library timing update", "The central library will remain open until 9 PM on weekdays.", "Campus Services", now.minusDays(4), "LOW", "Library Services"),
                announcement("announcement-005", "Holiday announcement", "The university will remain closed next Monday for the regional holiday.", "General", now.minusDays(5), "MEDIUM", "Administration"),
                announcement("announcement-006", "Internship opportunity", "A summer software internship opportunity is open for second and third-year students.", "Career", now.minusDays(6), "HIGH", "Career Development Centre"),
                announcement("announcement-007", "AI workshop registration", "Seats are available for the upcoming AI and Machine Learning Workshop.", "Events", now.minusDays(7), "MEDIUM", "AI Club"),
                announcement("announcement-008", "Exam timetable notification", "The end-semester examination timetable will be published next week.", "Academic", now.minusDays(8), "HIGH", "Academic Office"),
                announcement("announcement-009", "Department meeting", "Class representatives should attend the monthly department meeting.", "Department", now.minusDays(9), "LOW", "CSE Department"),
                announcement("announcement-010", "Campus recruitment drive", "A technology company recruitment drive is scheduled for final-year students.", "Placement", now.minusDays(10), "HIGH", "Placement Cell"));
        records.stream().filter(record -> !repository.existsByDemoKey(record.getDemoKey())).forEach(repository::save);
    }

    private Announcement announcement(String key, String title, String description, String category, LocalDateTime date, String priority, String author) {
        return Announcement.builder().demoKey(key).title(title).description(description).category(category).publishedAt(date)
                .priority(priority).department(category).author(author).status("Active").build();
    }

    private void seedMessages(CampusMessageRepository repository) {
        LocalDateTime now = LocalDateTime.now();
        List<CampusMessage> records = List.of(
                message("message-001", "Ananya Sharma", "Prof. Arvind Rao", "Java assignment deadline", "Sir, could you please clarify the submission deadline for the Java assignment?", 2, "Unread"),
                message("message-002", "Prof. Arvind Rao", "Ananya Sharma", "Re: Java assignment deadline", "The assignment must be submitted by Friday at 5 PM.", 1, "Read"),
                message("message-003", "Arjun Kumar", "Dr. Radhika Menon", "Data Science mentorship", "Could we discuss a roadmap for my first data science project?", 24, "Unread"),
                message("message-004", "Dr. Radhika Menon", "Arjun Kumar", "Re: Data Science mentorship", "Certainly. Please bring your project idea to the mentoring hour on Thursday.", 20, "Read"),
                message("message-005", "Priya Nair", "Placement Cell", "Placement registration", "Can fourth-year IT students register for the upcoming drive?", 30, "Pending"),
                message("message-006", "Placement Cell", "Priya Nair", "Re: Placement registration", "Yes, please complete the registration form before Wednesday.", 18, "Read"),
                message("message-007", "Rahul Krishnan", "CyberSec Club", "Workshop question", "Will the cybersecurity workshop include a hands-on lab?", 48, "Pending"),
                message("message-008", "Meera Iyer", "Prof. Suresh Babu", "Cloud project guidance", "Could you recommend a beginner-friendly cloud project?", 50, "Unread"),
                message("message-009", "Prof. Suresh Babu", "Meera Iyer", "Re: Cloud project guidance", "Try deploying a small Spring Boot API with a managed database.", 36, "Read"),
                message("message-010", "Kavya S", "Academic Office", "Attendance clarification", "Please help me understand the attendance update for the AI lab.", 72, "Pending"),
                message("message-011", "Vishnu Prasad", "Sports Committee", "Sports meet registration", "Our department team would like to register for volleyball.", 76, "Read"),
                message("message-012", "Harini M", "Dr. Lakshmi Iyer", "ML project discussion", "Could you review my proposed image classification project?", 96, "Unread"),
                message("message-013", "Sanjay Kumar", "Developer Club", "Hackathon registration", "Is team registration required for the campus hackathon?", 100, "Read"),
                message("message-014", "Aishwarya K", "Library Services", "Library timing", "Thank you for the extended library hours during assessments.", 120, "Read"),
                message("message-015", "Karthik S", "Prof. Mohan Joseph", "Algorithms guidance", "May I get suggestions for competitive programming practice?", 125, "Pending"),
                message("message-016", "Divya Prakash", "Career Development Centre", "Internship guidance", "Which skills should I highlight for the summer internship application?", 140, "Unread"));
        records.stream().filter(record -> !repository.existsByDemoKey(record.getDemoKey())).forEach(repository::save);
    }

    private CampusMessage message(String key, String sender, String receiver, String subject, String text, int hoursAgo, String status) {
        return CampusMessage.builder().demoKey(key).sender(sender).receiver(receiver).subject(subject).message(text)
                .timestamp(LocalDateTime.now().minusHours(hoursAgo)).status(status).build();
    }
}
