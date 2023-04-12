package com.example.demo.dao;

import com.example.demo.model.Locations;
import com.example.demo.model.Person;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();
    @Override
    public Optional<Person> insertPerson(UUID ID, Person person) {
        DB.add(new Person(ID, person.getName(), person.getEmail(), person.getPassword(), person.getLocation()));
        return Optional.ofNullable(person);
    }

    @Override
    public Optional<Person> addPerson(Person person) {
        return Optional.empty();
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonbyId(UUID ID) {
        Optional<Person> person_maybe= selectPersonbyId(ID);
        if(person_maybe.isEmpty())
            return 0;
        DB.remove( person_maybe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID ID, Person person) {
        return selectPersonbyId(ID).map(person1 -> {
            int indexofptodelete = DB.indexOf(person1);
            if(indexofptodelete>=0) {
                DB.set(indexofptodelete, new Person(ID, person.getName(), person.getEmail(), person.getPassword(), person.getLocation()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public Optional<Person> selectPersonbyId(UUID ID) {
        return DB.stream().filter(person -> person.getId().equals(ID)).findFirst();
    }
    @Override
    public Optional<Person> selectPersonbyName(String name) {
        return DB.stream().filter(person -> person.getName().equals(name)).findFirst();
    }
    @Override
    public Optional<Person> selectPersonbyEmail(String email){
        return DB.stream().filter(person -> person.getEmail().equals(email)).findFirst();
    }

    @Override
    public int addLocation(UUID id, Triple<Integer, Integer, Color> location) {
        return 0;
    }

    @Override
    public Optional<Triple<Integer, Integer, Color>> getLocation(UUID id) {
        return Optional.empty();
    }

    @Override
    public int updateLocation(UUID id, Triple<Integer, Integer, Color> location) {
        return 0;
    }

    @Override
    public List<Locations> selectAllLocations() {
        return null;
    }

}
