package com.Bakunov.OOPpractice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Bakunov.OOPpractice.model.Days;

@Repository
public interface DayRepository extends CrudRepository<Days, Integer>{

}
