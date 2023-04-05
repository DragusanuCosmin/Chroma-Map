package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    Optional<Person> insertPerson(UUID ID, Person person);
    default Optional<Person> addPerson(Person person) {
        UUID ID=UUID.randomUUID();
        return insertPerson(ID, person);
    }
    List<Person> selectAllPeople();
    int deletePersonbyId(UUID ID);
    int updatePerson(UUID ID,Person person);
    Optional<Person> selectPersonbyId(UUID ID);
}
