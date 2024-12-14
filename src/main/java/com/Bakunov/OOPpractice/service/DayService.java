package com.Bakunov.OOPpractice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bakunov.OOPpractice.DTO.DTOday;
import com.Bakunov.OOPpractice.DTO.DTOlesson;
import com.Bakunov.OOPpractice.model.Days;
import com.Bakunov.OOPpractice.model.Lesson;
import com.Bakunov.OOPpractice.repository.DayRepository;
import com.Bakunov.OOPpractice.repository.LessonRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ModelMapper modelMapper;

    private LessonService lessonService;

    public DayService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    public DTOday convertToDto(Days day) {
        DTOday dto = modelMapper.map(day, DTOday.class);
        dto.setId(day.getId());
        List<DTOlesson> lessons = day.getLessons()
                                     .stream()
                                     .map(lessonService::convertToDto)
                                     .toList();

        dto.setLessons(lessons);
        return dto;
    }

        @Transactional
    public Days create(String name, List<Integer> subjectIds) {
        Iterable<Lesson> lessons = lessonRepository.findAllById(subjectIds);
        if (((Collection<?>)lessons).size() <= 0) {
            throw new RuntimeException("Some lessons not found");
        }
        List<Lesson> lessonsList = new ArrayList<>();
        lessons.forEach(lessonsList::add);
    Days day = new Days(name, lessonsList);
    return dayRepository.save(day);
    }

    public List<DTOday> readAll() {
        Iterable<Days> days = dayRepository.findAll();
        List<DTOday> dtos = new ArrayList<>();
        for (Days day : days) {
            dtos.add(convertToDto(day));
        }
        return dtos;
    }

    public DTOday read(int id) {
        return dayRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Day not found"));
    }

    public Days update(String name, List<Integer> subjectIds, int id) {
        Optional<Days> existingDayOpt = dayRepository.findById(id);
        if (existingDayOpt.isPresent()) {
            Days existingDay = existingDayOpt.get();
            Iterable<Lesson> lessons = lessonRepository.findAllById(subjectIds);
            List<Lesson> lessonsList = new ArrayList<>();
            lessons.forEach(lessonsList::add);
            existingDay.setName(name);
            existingDay.setLessons(lessonsList);
            return dayRepository.save(existingDay);
        } else {
            throw new RuntimeException("Day not found");
        }
    }

    public void delete(int id) {
        if (!dayRepository.existsById(id)) {
            throw new RuntimeException("Day with ID " + id + " not found");
        }
        dayRepository.deleteById(id);
    }

    public boolean addSubjects(int id, List<Integer> subjectIds) {
        Optional<Days> dayOpt = dayRepository.findById(id);
        if (dayOpt.isPresent()) {
            Days day = dayOpt.get();
            List<Lesson> existingSubjects = day.getLessons();
            Iterable<Lesson> newSubjects = lessonRepository.findAllById(subjectIds);
            List<Lesson> lessonsList = new ArrayList<>();
            newSubjects.forEach(lessonsList::add);
            existingSubjects.addAll(lessonsList);
            day.setLessons(existingSubjects);
            dayRepository.save(day);
            return true;
        }
        return false;
    }
}
