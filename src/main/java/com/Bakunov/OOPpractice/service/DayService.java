package com.Bakunov.OOPpractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bakunov.OOPpractice.model.Days;
import com.Bakunov.OOPpractice.repository.DayRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {

    @Autowired
    private DayRepository dayRepository;

    // Create a new Day with name and subject IDs
    public Days create(String name, List<Integer> subjectIds) {
        Days day = new Days(name, subjectIds);
        return dayRepository.save(day);
    }

    // Read all days
    public Iterable<Days> readAll() {
        return dayRepository.findAll();
    }

    // Read a single Day by ID
    public Optional<Days> read(int id) {
        return dayRepository.findById(id);
    }

    // Update an existing Day
    public boolean update(Days updatedDay, int id) {
        Optional<Days> existingDayOpt = dayRepository.findById(id);
        if (existingDayOpt.isPresent()) {
            Days existingDay = existingDayOpt.get();
            existingDay.setName(updatedDay.getName());
            existingDay.setSubjects(updatedDay.getSubjects());
            dayRepository.save(existingDay);
            return true;
        } else {
            throw new RuntimeException("Day not found");
        }
    }

    // Add new subjects to an existing Day
    public boolean addSubjects(int id, List<Integer> newSubjects) {
        Optional<Days> existingDayOpt = dayRepository.findById(id);
        if (existingDayOpt.isPresent()) {
            Days existingDay = existingDayOpt.get();
            List<Integer> subjects = existingDay.getSubjects();

            if (subjects == null) {
                subjects = new ArrayList<>();
            }

            subjects.addAll(newSubjects);
            existingDay.setSubjects(subjects);
            dayRepository.save(existingDay);
            return true;
        } else {
            throw new RuntimeException("Day not found");
        }
    }

    // Delete a Day by ID
    public boolean delete(int id) {
        if (dayRepository.existsById(id)) {
            dayRepository.deleteById(id);
            return true;
        }
        return false;
    }
}