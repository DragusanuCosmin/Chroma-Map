package com.example.demo.dao;

import com.example.demo.model.Locations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface LocationsDao {
    int addLocation(Locations location);
    Optional<List<Locations>> getLocation(UUID id);
    int updateLocation(UUID ID, Locations location);
    List<Locations> selectAllLocations();
}
