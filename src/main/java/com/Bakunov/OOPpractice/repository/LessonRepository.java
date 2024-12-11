package com.Bakunov.OOPpractice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Bakunov.OOPpractice.model.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer>{

}
