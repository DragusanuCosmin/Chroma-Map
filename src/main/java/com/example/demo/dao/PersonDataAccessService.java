package com.example.demo.dao;

import com.example.demo.api.PersonController;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("realDao")
public class PersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID ID, Person person) {
            DB.add(new Person(ID, person.getName(), person.getEmail(), person.getPassword()));
            return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return List.of(new Person(UUID.randomUUID(), "John Doe", "jdoe@example.com", "password"));
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
        return 0;
    }

    @Override
    public Optional<Person> selectPersonbyId(UUID ID) {
        return Optional.empty();
    }
}
