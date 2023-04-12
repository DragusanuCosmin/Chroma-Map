package com.example.demo.dao;

import com.example.demo.model.Locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("locationsDao")
public class LocationDataAccessService implements LocationsDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired

    public LocationDataAccessService(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }
    @Override
    public int addLocation(Locations location) {
            final String sql = "INSERT INTO locations (location_id, x, y, color) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sql, location.getLocation_id().toString(), location.getX(), location.getY(), location.getColor());
    }

    @Override
    public Optional<List<Locations>> getLocation(UUID id) {
        try {
            final String sql = "SELECT * FROM locations WHERE location_id = ?";
            List<Locations> locationsList = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
                String x = rs.getString("x");
                String y = rs.getString("y");
                String color = rs.getString("color");
                return new Locations(id, Integer.parseInt(x), Integer.parseInt(y), color);
            });
            return Optional.of(locationsList);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    @Override
    public List<Locations> selectAllLocations() {
        final String sql = "SELECT * FROM locations";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UUID locationId = UUID.fromString(rs.getString("location_id"));
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            String color = rs.getString("color");
            return new Locations(locationId, x, y, color);
        });
    }
    @Override
    public int updateLocation(UUID id ,Locations location) {
        final String sql = "UPDATE locations SET x = ?, y = ?, color = ? WHERE location_id = ?";
        return jdbcTemplate.update(sql, location.getX(), location.getY(), location.getColor(), id.toString());
    }

}
