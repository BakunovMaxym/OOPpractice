package com.Bakunov.OOPpractice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Bakunov.OOPpractice.DTO.DTOday;
import com.Bakunov.OOPpractice.DTO.DTOlesson;
import com.Bakunov.OOPpractice.model.Days;
import com.Bakunov.OOPpractice.service.DayService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/days")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DayController {

    private final DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping
    public ResponseEntity<DTOday> create(@Valid @RequestBody DTOday dtoDay) {
        Days createdDay = dayService.create(dtoDay.getName(),
                dtoDay.getLessons().stream()
                        .map(DTOlesson::getId)
                        .toList());
        DTOday responseDto = dayService.convertToDto(createdDay);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<DTOday>> readAll() {
        List<DTOday> days = dayService.readAll();
        return days.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(days);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOday> read(@PathVariable int id) {
        try {
            DTOday day = dayService.read(id);
            return ResponseEntity.status(HttpStatus.OK).body(day);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOday> update(@PathVariable int id, @Valid @RequestBody DTOday dtoDay) {
        try {
            Days updatedDay = dayService.update(dtoDay.getName(),
                    dtoDay.getLessons().stream()
                            .map(DTOlesson::getId)
                            .toList(),
                    id);
            DTOday responseDto = dayService.convertToDto(updatedDay); // Перетворення в DTO
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            dayService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/subjects")
    public ResponseEntity<String> addSubjects(@PathVariable int id, @RequestBody List<Integer> subjectIds) {
        boolean updated = dayService.addSubjects(id, subjectIds);
        return updated
                ? ResponseEntity.status(HttpStatus.OK).body("Subjects successfully added")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error trying to add subjects");
    }
}