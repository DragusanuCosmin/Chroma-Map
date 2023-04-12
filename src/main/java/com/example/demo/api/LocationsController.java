package com.example.demo.api;

import com.example.demo.model.Locations;
import com.example.demo.service.LocationsService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:3001/")
@RequestMapping("/api/v1/locations")
@RestController
public class LocationsController {
    private final LocationsService locationsService;
    @Autowired
    public  LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }
    @PostMapping
    public void addLocation(@NotNull @RequestBody Locations location) {
        locationsService.addLocation(location);
    }

    @GetMapping("/{id}/location")
    public Optional<List<Locations>>  getLocation(@PathVariable UUID id) {
        return locationsService.getLocation(id);
    }

    @PutMapping("/{id}/location")
    public int updateLocation(@PathVariable UUID id, @RequestBody Locations location) {
        return locationsService.updateLocation(id, location);
    }
    @GetMapping
    public List<Locations> selectAllLocations() {
        return locationsService.selectAllLocations();
    }
}
