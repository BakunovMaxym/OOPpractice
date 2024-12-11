package com.Bakunov.OOPpractice.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Days {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    
    @ManyToMany(cascade = jakarta.persistence.CascadeType.ALL)
    @JoinTable(
        name = "day_lessons",
        joinColumns = @JoinColumn(name = "day_id"),
        inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lesson> lessons;

    
    public Days() {}

    public Days(String name, List<Lesson> lessons) {
        this.name = name;
        this.lessons = lessons != null ? lessons : new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

   public void setLessons(List<Lesson> lessons) {
       this.lessons = lessons;
   }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}

