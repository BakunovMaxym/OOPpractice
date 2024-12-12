package com.Bakunov.OOPpractice.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DTOlesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotNull(message = "teacher's name cannot be null")
    @NotBlank(message = "teacher's name cannot be blank")
    private String teacher;

    @NotNull(message = "classroom cannot be null")
    @DecimalMax(value = "600", inclusive = false, message = "there are not so many classrooms")
    private int classroom;

    public DTOlesson() {
    }

    public DTOlesson(String name, String teacher, int classroom) {
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
