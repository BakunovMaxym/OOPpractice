package com.Bakunov.OOPpractice.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String teacher;
    private int classroom;
    
    @ManyToMany(mappedBy = "lessons")
    private List<Days> days;

    public Lesson() {}

    public Lesson(String name, String teacher, int classroom){
        this.name = name;
        this.teacher = teacher;
        this.classroom = classroom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public void setDays(List<Days> days) {
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClassroom() {
        return classroom;
    }

    public String getTeacher() {
        return teacher;
    }
    public List<Days> getDays() {
        return days;
    }
}


