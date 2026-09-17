package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.model.Announcement;
import com.campuspulse.campus_pulse.service.AnnouncementService;
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
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService service;
    @GetMapping public List<Announcement> findAll(@RequestParam(required = false) String search) { return service.findAll(search); }
    @GetMapping("/important") public List<Announcement> findImportant() { return service.findImportant(); }
    @GetMapping("/active") public List<Announcement> findActive() { return service.findActive(); }
    @GetMapping("/{id}") public Announcement findById(@PathVariable String id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Announcement create(@Valid @RequestBody Announcement announcement) { return service.save(announcement); }
    @PutMapping("/{id}") public Announcement update(@PathVariable String id, @Valid @RequestBody Announcement announcement) { return service.update(id, announcement); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable String id) { service.delete(id); }
}
