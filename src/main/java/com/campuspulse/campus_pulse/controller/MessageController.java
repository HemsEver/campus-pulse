package com.campuspulse.campus_pulse.controller;

import com.campuspulse.campus_pulse.model.CampusMessage;
import com.campuspulse.campus_pulse.service.MessageService;
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
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;
    @GetMapping public List<CampusMessage> findAll(@RequestParam(required = false) String search) { return service.findAll(search); }
    @GetMapping("/{id}") public CampusMessage findById(@PathVariable String id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public CampusMessage create(@Valid @RequestBody CampusMessage message) { return service.save(message); }
    @PutMapping("/{id}") public CampusMessage update(@PathVariable String id, @Valid @RequestBody CampusMessage message) { return service.update(id, message); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable String id) { service.delete(id); }
}
