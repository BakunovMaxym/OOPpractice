package com.Bakunov.OOPpractice.DTO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DTOday {
 @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "day's name cannot be blank")
    private String name;
    
    @ElementCollection
    private List<DTOlesson> lessons = new ArrayList<>();

    
    public DTOday() {}

    public DTOday(int id, String name, List<DTOlesson> lessons) {
        this.id=id;
        this.name = name;
        this.lessons = lessons;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

   public void setLessons(List<DTOlesson> lessons) {
       this.lessons = lessons;
   }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<DTOlesson> getLessons() {
        return lessons;
    }
}
