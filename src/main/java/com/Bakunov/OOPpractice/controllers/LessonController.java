package com.Bakunov.OOPpractice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bakunov.OOPpractice.DTO.DTOlesson;
import com.Bakunov.OOPpractice.model.Lesson;
import com.Bakunov.OOPpractice.service.LessonService;

import jakarta.validation.Valid;

@RestController
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/lessons")
    public ResponseEntity<DTOlesson> create(@Valid @RequestBody DTOlesson dtolesson) {
        Lesson lesson = lessonService.convertToEntity(dtolesson);
        Lesson created = lessonService.create(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.convertToDto(created));
    }

    @GetMapping("/lessons")
    public ResponseEntity<List<DTOlesson>> readAll() {
        Iterable<Lesson> lessons = lessonService.readAll();
        List<DTOlesson> dtoLessons = new ArrayList<>();
        lessons.forEach(lesson -> dtoLessons.add(lessonService.convertToDto(lesson)));

        return dtoLessons.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(dtoLessons);
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<DTOlesson> read(@PathVariable(name = "id") int id) {
        Optional<Lesson> lesson = lessonService.read(id);
        return lesson.map(value -> ResponseEntity.status(HttpStatus.OK).body(lessonService.convertToDto(value)))
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<DTOlesson> update(@PathVariable(name = "id") int id, @Valid @RequestBody DTOlesson dtolesson) {
        Lesson lesson = lessonService.convertToEntity(dtolesson);
        boolean updated = lessonService.update(lesson, id);

        if (updated) {
            Lesson updatedLesson = lessonService.read(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
            return ResponseEntity.status(HttpStatus.OK).body(lessonService.convertToDto(updatedLesson));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
        boolean deleted = lessonService.delete(id);
        return deleted
                ? ResponseEntity.status(HttpStatus.OK).body("Successfully deleted")
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Error trying to delete lesson");
    }
}
