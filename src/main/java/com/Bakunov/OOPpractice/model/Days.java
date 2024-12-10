package com.Bakunov.OOPpractice.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Days {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ElementCollection
    private List<Integer> subjects;

    
    public Days() {}

    public Days(String name, List<Integer> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public void setName(String name) {
        this.name = name;
    }

   public void setSubjects(List<Integer> subjects) {
       this.subjects = subjects;
   }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getSubjects() {
        return subjects;
    }
}
