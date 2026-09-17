package com.campuspulse.campus_pulse.service;

import com.campuspulse.campus_pulse.exception.ResourceNotFoundException;
import com.campuspulse.campus_pulse.model.CampusEvent;
import com.campuspulse.campus_pulse.repository.CampusEventRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final CampusEventRepository repository;

    public List<CampusEvent> findUpcoming(String search) {
        if (search != null && !search.isBlank()) {
            return repository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(search, search);
        }
        return repository.findByEventDateAfterOrderByEventDateAsc(LocalDateTime.now());
    }

    public List<CampusEvent> findAll() {
        return repository.findAll();
    }

    public long countUpcoming() { return repository.findByEventDateAfterOrderByEventDateAsc(LocalDateTime.now()).size(); }

    public List<CampusEvent> findByCategory(String category) {
        return repository.findByCategoryContainingIgnoreCaseAndEventDateAfterOrderByEventDateAsc(category, LocalDateTime.now());
    }

    public CampusEvent findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found: " + id));
    }

    public boolean hasStudentConflict(String studentId, LocalDateTime candidateDate) {
        if (studentId == null || studentId.isBlank() || candidateDate == null) {
            return false;
        }
        return repository.findAll().stream()
                .filter(event -> event.getRegisteredStudentIds() != null && event.getRegisteredStudentIds().contains(studentId))
                .anyMatch(existing -> hasTimeConflict(
                        CampusEvent.builder().eventDate(candidateDate).build(), existing));
    }

    public static boolean hasTimeConflict(CampusEvent candidate, CampusEvent existing) {
        if (candidate == null || existing == null || candidate.getEventDate() == null || existing.getEventDate() == null) {
            return false;
        }
        LocalDateTime candidateStart = candidate.getEventDate();
        LocalDateTime candidateEnd = candidateStart.plusHours(2);
        LocalDateTime existingStart = existing.getEventDate();
        LocalDateTime existingEnd = existingStart.plusHours(2);
        return candidateStart.isBefore(existingEnd) && existingStart.isBefore(candidateEnd);
    }

    public CampusEvent registerStudent(String eventId, String studentId) {
        CampusEvent current = findById(eventId);
        if (current.getRegisteredStudentIds() == null) {
            current.setRegisteredStudentIds(new ArrayList<>());
        }
        if (!current.getRegisteredStudentIds().contains(studentId)) {
            current.getRegisteredStudentIds().add(studentId);
        }
        return repository.save(current);
    }

    public CampusEvent save(CampusEvent event) { return repository.save(event); }
    public CampusEvent update(String id, CampusEvent event) { findById(id); event.setId(id); return repository.save(event); }
    public void delete(String id) { repository.delete(findById(id)); }
}
