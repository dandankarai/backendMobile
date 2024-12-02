package com.example.backend.controller;
import com.example.backend.model.Event;
import com.example.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getUserById(@PathVariable Long id) {
        Optional<Event> user = eventRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateUser(@PathVariable Long id, @RequestBody Event eventDetails) {
        Optional<Event> optionalUser = eventRepository.findById(id);
        if (optionalUser.isPresent()) {
            Event event = optionalUser.get();
            event.setNameEvent(eventDetails.getNameEvent());
            event.setTypeEvent(eventDetails.getTypeEvent());
            event.setSizeEvent(eventDetails.getSizeEvent());
            event.setLocation(eventDetails.getLocation());
            return ResponseEntity.ok(eventRepository.save(event));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}