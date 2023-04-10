package com.example.demo.dao;

import com.example.demo.model.Locations;
import com.example.demo.model.Person;
import org.antlr.v4.runtime.misc.Triple;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    Optional<Person> insertPerson(UUID id, Person person);
    Optional<Person> addPerson(Person person);

    List<Person> selectAllPeople();
    int deletePersonbyId(UUID id);
    int updatePerson(UUID id,Person person);
    Optional<Person> selectPersonbyId(UUID id);
    Optional<Person> selectPersonbyName(String name);
    Optional<Person> selectPersonbyEmail(String email);
    int addLocation(UUID id, Triple<Integer, Integer, Color> location);
    Optional<Triple<Integer, Integer, Color>> getLocation(UUID id);
    int updateLocation(UUID id, Triple<Integer, Integer, Color> location);
    List<Locations> selectAllLocations();
}
