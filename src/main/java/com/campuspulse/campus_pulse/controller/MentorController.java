package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.model.Mentor;
import com.campuspulse.campus_pulse.service.MentorService;
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
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService service;
    @GetMapping public List<Mentor> findAll(@RequestParam(required = false) String search) { return service.findAll(search); }
    @GetMapping("/available") public List<Mentor> findAvailable() { return service.findAvailable(); }
    @GetMapping("/{id}") public Mentor findById(@PathVariable String id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Mentor create(@Valid @RequestBody Mentor mentor) { return service.save(mentor); }
    @PutMapping("/{id}") public Mentor update(@PathVariable String id, @Valid @RequestBody Mentor mentor) { return service.update(id, mentor); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable String id) { service.delete(id); }
}
