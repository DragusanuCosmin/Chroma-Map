package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

}
