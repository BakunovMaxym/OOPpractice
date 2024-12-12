package com.Bakunov.OOPpractice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bakunov.OOPpractice.DTO.DTOlesson;
import com.Bakunov.OOPpractice.model.Lesson;
import com.Bakunov.OOPpractice.repository.LessonRepository;

import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public DTOlesson convertToDto(Lesson lesson){
        return modelMapper.map(lesson, DTOlesson.class);
    }

    public Lesson convertToEntity(DTOlesson dtOlesson){
        return modelMapper.map(dtOlesson, Lesson.class);
    }

    public Lesson create(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Iterable<Lesson> readAll() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> read(int id) {
        return lessonRepository.findById(id);
    }

    public boolean update(Lesson lesson, int id) {
            Optional <Lesson> existinglesson = lessonRepository.findById(id);
            if (existinglesson.isPresent()) {
                Lesson existingLesson = existinglesson.get();
                existingLesson.setName(lesson.getName());
                existingLesson.setTeacher(lesson.getTeacher());
                existingLesson.setClassroom(lesson.getClassroom());
                lessonRepository.save(existingLesson);
                return true;
            } else {
                throw new RuntimeException("Lesson not found");
            }
    }

    public boolean delete(int id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
