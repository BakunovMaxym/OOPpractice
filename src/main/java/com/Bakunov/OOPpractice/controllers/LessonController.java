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

import com.Bakunov.OOPpractice.model.Lesson;
import com.Bakunov.OOPpractice.service.LessonService;

@RestController
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }


    @PostMapping("/lessons")
    public ResponseEntity<String> create(@RequestBody Lesson lesson) {
        lessonService.create(lesson);
        System.out.println(lesson);
        return new ResponseEntity<>("Successfuly added", HttpStatus.CREATED);
    }


    @GetMapping(value = "/lessons")
    public ResponseEntity<List<Lesson>> read() {
        final Iterable<Lesson> lessons = lessonService.readAll();
        System.out.println(lessons);
        List<Lesson> lessonList = new ArrayList<>();

        lessons.forEach(lessonList::add);

        if (!lessonList.isEmpty()) {
            return new ResponseEntity<>(lessonList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping(value = "/lessons/{id}")
    public ResponseEntity<Lesson> read(@PathVariable(name = "id") int id) {
        final Optional<Lesson> lesson = lessonService.read(id);

        Lesson respLesson = lesson.orElseThrow(() -> new RuntimeException("Lesson not found"));

       return new ResponseEntity<>(respLesson, HttpStatus.OK);
    }


    @PutMapping(value = "/lessons/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") int id, @RequestBody Lesson lesson) {
        final boolean updated = lessonService.update(lesson, id);
    
        return updated
                ? new ResponseEntity<>("Successfuly edited", HttpStatus.OK)
                : new ResponseEntity<>("Error trying to edit lesson", HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(value = "/lessons/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
       final boolean deleted = lessonService.delete(id);

       return deleted
               ? new ResponseEntity<>("Successfuly deleted", HttpStatus.OK)
               : new ResponseEntity<>("Error trying to delete lesson", HttpStatus.NOT_MODIFIED);
    }
}
