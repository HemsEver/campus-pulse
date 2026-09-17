package com.campuspulse.campus_pulse.service;

import com.campuspulse.campus_pulse.exception.ResourceNotFoundException;
import com.campuspulse.campus_pulse.model.Mentor;
import com.campuspulse.campus_pulse.repository.MentorRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository repository;

    public List<Mentor> findAll(String search) {
        return search == null || search.isBlank()
                ? repository.findAll()
                : repository.findByExpertiseContainingIgnoreCaseOrDepartmentContainingIgnoreCase(search, search);
    }

    public List<Mentor> findAvailable() { return repository.findByAvailabilityIgnoreCase("Available"); }
    public List<Mentor> findMatching(String expertise) {
        return repository.findByExpertiseContainingIgnoreCaseOrMentoringAreasContainingIgnoreCase(expertise, expertise);
    }
    public long count() { return repository.count(); }
    public Mentor findById(String id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentor not found: " + id)); }
    public boolean hasStudentBookingConflict(String mentorId, String studentId) {
        Mentor mentor = findById(mentorId);
        return mentor.getBookedStudentIds() != null && mentor.getBookedStudentIds().contains(studentId);
    }
    public Mentor bookMentor(String mentorId, String studentId) {
        Mentor mentor = findById(mentorId);
        if (mentor.getBookedStudentIds() == null) {
            mentor.setBookedStudentIds(new ArrayList<>());
        }
        if (!mentor.getBookedStudentIds().contains(studentId)) {
            mentor.getBookedStudentIds().add(studentId);
        }
        return repository.save(mentor);
    }
    public Mentor save(Mentor mentor) { return repository.save(mentor); }
    public Mentor update(String id, Mentor mentor) { findById(id); mentor.setId(id); return repository.save(mentor); }
    public void delete(String id) { repository.delete(findById(id)); }
}
