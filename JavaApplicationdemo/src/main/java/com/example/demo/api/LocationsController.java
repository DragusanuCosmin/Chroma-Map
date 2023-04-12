package com.example.demo.api;

import com.example.demo.model.Locations;
import com.example.demo.service.PersonService;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.awt.Color;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:3001/")
@RequestMapping("/api/v1/locations")
@RestController
public class LocationsController {
    private final PersonService personService;
    @Autowired
    public  LocationsController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping("/{id}/location")
    public void addLocation(@PathVariable UUID id, @RequestBody Triple<Integer, Integer, Color> location) {
        personService.addLocation(id, location);
    }

    @GetMapping("/{id}/location")
    public Optional<Triple<Integer, Integer, Color>> getLocation(@PathVariable UUID id) {
        return personService.getLocation(id);
    }

    @PutMapping("/{id}/location")
    public int updateLocation(@PathVariable UUID id, @RequestBody Triple<Integer, Integer, Color> location) {
        return personService.updateLocation(id, location);
    }
    @GetMapping
    public List<Locations> selectAllLocations() {
        return personService.selectAllLocations();
    }
}
