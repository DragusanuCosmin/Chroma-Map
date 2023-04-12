package com.example.demo.api;

import com.example.demo.model.Locations;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.awt.Color;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:3001/")
@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @DeleteMapping("/{id}")
    public int deletePerson(@PathVariable UUID id) {
        return personService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public int updatePerson(@PathVariable UUID id,  @NonNull @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @GetMapping(path="{id}")
    public Person selectPersonById(@PathVariable("id") UUID id) {
        return personService.selectPersonbyId(id).orElse(null);
    }

    @GetMapping("{name}")
    public Person selectPersonByName(@PathVariable("name") String name) {
        return personService.selectPersonbyName(name).orElse(null);
    }
}
