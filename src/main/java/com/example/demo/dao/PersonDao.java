package com.example.demo.dao;

import com.example.demo.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    Optional<Person> insertPerson(UUID id, Person person);
    int addPerson(Person person);

    List<Person> selectAllPeople();
    int deletePersonbyId(UUID id);
    int updatePerson(UUID id,Person person);
    Optional<Person> selectPersonbyId(UUID id);
    Optional<Person> selectPersonbyName(String name);
    Optional<Person> selectPersonbyEmail(String email);

}
