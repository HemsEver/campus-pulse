package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.model.CampusEvent;
import com.campuspulse.campus_pulse.service.EventService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    @GetMapping public List<CampusEvent> findUpcoming(@RequestParam(required = false) String search) { return service.findUpcoming(search); }
    @GetMapping("/all") public List<CampusEvent> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public CampusEvent findById(@PathVariable String id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public CampusEvent create(@Valid @RequestBody CampusEvent event) { return service.save(event); }
    @PostMapping("/register") public EventRegistrationResponse register(@RequestBody EventRegistrationRequest request) {
        CampusEvent event = service.findById(request.eventId());
        String studentId = request.studentId();
        boolean conflict = service.hasStudentConflict(studentId, event.getEventDate());
        CampusEvent updated = service.registerStudent(event.getId(), studentId);
        return new EventRegistrationResponse(updated.getId(), studentId, updated.getTitle(), conflict, "Registered successfully");
    }
    @PutMapping("/{id}") public CampusEvent update(@PathVariable String id, @Valid @RequestBody CampusEvent event) { return service.update(id, event); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable String id) { service.delete(id); }

    public record EventRegistrationRequest(String eventId, String studentId) { }
    public record EventRegistrationResponse(String eventId, String studentId, String eventTitle, boolean conflict, String status) { }
}
