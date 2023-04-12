package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Locations;
import com.example.demo.model.Person;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;
    @Autowired
    public PersonService(@Qualifier("realDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public Optional<Person> addPerson(Person person) {
        return personDao.addPerson(person);
    }
    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }
    public int deletePerson(UUID ID) {
        return personDao.deletePersonbyId(ID);
}
    public int updatePerson(UUID ID, Person person) {
        return personDao.updatePerson(ID, person);
}
    public Optional<Person> selectPersonbyId(UUID ID) {
        return personDao.selectPersonbyId(ID);
    }
    public Optional<Person> selectPersonbyName(String name) {
        return personDao.selectPersonbyName(name);
    }
    public Optional<Person> selectPersonbyEmail(String email){
        return personDao.selectPersonbyEmail(email);
    }
    public int addLocation(UUID ID, Triple<Integer, Integer, Color> location) {
        return personDao.addLocation(ID, location);
    }
    public Optional<Triple<Integer, Integer, Color>> getLocation(UUID ID) {
        return personDao.getLocation(ID);
    }
    public int updateLocation(UUID ID, Triple<Integer, Integer, Color> location) {
        return personDao.updateLocation(ID, location);
    }
    public List<Locations> selectAllLocations(){
        return personDao.selectAllLocations();
    }

}
