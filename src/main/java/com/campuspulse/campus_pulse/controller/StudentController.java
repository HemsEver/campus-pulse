package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.model.Student;
import com.campuspulse.campus_pulse.service.StudentService;
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
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping public List<Student> findAll(@RequestParam(required = false) String search) { return service.findAll(search); }
    @GetMapping("/match") public List<StudentService.StudentMatchResult> match(@RequestParam String skills) {
        List<String> requestedSkills = skills == null || skills.isBlank() ? List.of() : List.of(skills.split(","));
        return service.buildMatchResults(service.findAll(null), requestedSkills, null);
    }
    @GetMapping("/{id}") public Student findById(@PathVariable String id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Student create(@Valid @RequestBody Student student) { return service.save(student); }
    @PutMapping("/{id}") public Student update(@PathVariable String id, @Valid @RequestBody Student student) { return service.update(id, student); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable String id) { service.delete(id); }
}
