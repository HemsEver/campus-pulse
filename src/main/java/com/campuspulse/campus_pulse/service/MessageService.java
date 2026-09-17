package com.campuspulse.campus_pulse.service;

import com.campuspulse.campus_pulse.exception.ResourceNotFoundException;
import com.campuspulse.campus_pulse.model.CampusMessage;
import com.campuspulse.campus_pulse.repository.CampusMessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final CampusMessageRepository repository;

    public List<CampusMessage> findAll() { return repository.findAll(); }
    public List<CampusMessage> findAll(String search) {
        return search == null || search.isBlank()
                ? repository.findAll()
                : repository.findBySenderContainingIgnoreCaseOrReceiverContainingIgnoreCaseOrSubjectContainingIgnoreCase(search, search, search);
    }
    public CampusMessage findById(String id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found: " + id)); }
    public CampusMessage save(CampusMessage message) { return repository.save(message); }
    public CampusMessage update(String id, CampusMessage message) { findById(id); message.setId(id); return repository.save(message); }
    public void delete(String id) { repository.delete(findById(id)); }
    public long countPending() { return repository.countByStatusIgnoreCase("Pending"); }
}
