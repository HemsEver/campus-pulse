package com.campuspulse.campus_pulse.service;

import com.campuspulse.campus_pulse.exception.ResourceNotFoundException;
import com.campuspulse.campus_pulse.model.Announcement;
import com.campuspulse.campus_pulse.repository.AnnouncementRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository repository;

    public List<Announcement> findAll(String search) {
        return search == null || search.isBlank()
                ? repository.findAll()
                : repository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(search, search);
    }

    public List<Announcement> findImportant() { return repository.findByPriorityIgnoreCaseOrderByPublishedAtDesc("High"); }
    public List<Announcement> findLatest() { return repository.findByOrderByPublishedAtDesc(); }
    public List<Announcement> findActive() { return repository.findByStatusIgnoreCaseOrderByPublishedAtDesc("Active"); }
    public long countActive() { return findActive().size(); }
    public Announcement findById(String id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement not found: " + id)); }
    public Announcement save(Announcement announcement) { return repository.save(announcement); }
    public Announcement update(String id, Announcement announcement) { findById(id); announcement.setId(id); return repository.save(announcement); }
    public void delete(String id) { repository.delete(findById(id)); }
}
