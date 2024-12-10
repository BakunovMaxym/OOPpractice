package com.Bakunov.OOPpractice.models;
import java.time.LocalDate;

public class Lesson {
    private String name;
    private int orderNumber;
    private LocalDate date;
    private String teacher;
    private int classroom;
    
    public void setName(String name) {
        this.name = name;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getClassroom() {
        return classroom;
    }

    public String getTeacher() {
        return teacher;
    }
}
