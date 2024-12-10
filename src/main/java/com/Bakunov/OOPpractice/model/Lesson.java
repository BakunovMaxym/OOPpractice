package com.Bakunov.OOPpractice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String teacher;
    private int classroom;
    
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
}
