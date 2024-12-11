package com.Bakunov.OOPpractice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Bakunov.OOPpractice.model.Days;
import com.Bakunov.OOPpractice.service.DayService;

@RestController
@RequestMapping("/days")
public class DayController {

    private final DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Days day) {
        dayService.create(day.getName(), day.getSubjects());
        return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Days>> readAll() {
        Iterable<Days> days = dayService.readAll();
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Days> read(@PathVariable int id) {
        Optional<Days> day = dayService.read(id);
        return day.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                  .orElseThrow(() -> new RuntimeException("Day not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Days day) {
        boolean updated = dayService.update(day, id);
        return updated ? new ResponseEntity<>("Successfully edited", HttpStatus.OK)
                       : new ResponseEntity<>("Error trying to edit day", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        boolean deleted = dayService.delete(id);
        return deleted ? new ResponseEntity<>("Successfully deleted", HttpStatus.OK)
                       : new ResponseEntity<>("Error trying to delete day", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}/subjects")
    public ResponseEntity<String> addSubjects(@PathVariable int id, @RequestBody List<Integer> subjects) {
        boolean updated = dayService.addSubjects(id, subjects);
        return updated ? new ResponseEntity<>("Subjects successfully added", HttpStatus.OK)
                       : new ResponseEntity<>("Error trying to add subjects", HttpStatus.BAD_REQUEST);
    }
}