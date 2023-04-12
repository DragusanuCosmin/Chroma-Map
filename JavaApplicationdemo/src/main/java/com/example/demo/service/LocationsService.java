package com.example.demo.service;
import com.example.demo.dao.LocationsDao;
import com.example.demo.model.Locations;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class LocationsService {
    private final LocationsDao locationsDao;
    @Autowired
    public LocationsService(@Qualifier("locationsDao") LocationsDao locationsDao) {
        this.locationsDao = locationsDao;
    }
    public int addLocation( Locations location) {
        return locationsDao.addLocation(location);
    }
    public Optional<List<Locations>> getLocation(UUID ID) {
        return locationsDao.getLocation(ID);
    }
    public int updateLocation(UUID ID, Locations location) {
        return locationsDao.updateLocation(ID, location);
    }
    public List<Locations> selectAllLocations(){
        return locationsDao.selectAllLocations();
    }

}
