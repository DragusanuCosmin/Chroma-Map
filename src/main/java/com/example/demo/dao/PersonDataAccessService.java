package com.example.demo.dao;
import com.example.demo.model.Locations;
import com.example.demo.model.Person;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("realDao")
public class PersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDataAccessService(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    @Override
    public Optional<Person> insertPerson(UUID ID, Person person) {
        final String sql = "INSERT INTO person (id, name, email, password) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, ID.toString(), person.getName(), person.getEmail(), person.getPassword());
            return Optional.ofNullable(person);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public  Optional<Person> addPerson( Person person) {
        UUID ID=UUID.randomUUID();
        return insertPerson(ID, person);
        }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT p.id, p.name, p.email, p.password, l.location_id, l.x, l.y, c.red, c.green, c.blue, c.transparency " +
                "FROM person p " +
                "JOIN locations l ON p.id = l.id " +
                "JOIN colors c ON l.location_id = c.location_id";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            UUID locationId = UUID.fromString(rs.getString("location_id"));
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            int red = rs.getInt("red");
            int green = rs.getInt("green");
            int blue = rs.getInt("blue");
            float transparency = rs.getFloat("transparency");
            Color color = new Color(red, green, blue, Math.round(transparency * 255));
            Locations location = new Locations(locationId, x, y, color);
            return new Person(id, name, email, password, location);
        });
    }


    @Override
    public int deletePersonbyId(UUID id) {
        final String sql = "DELETE FROM person WHERE id= ?";
        jdbcTemplate.update(sql, id.toString());
        System.out.println(jdbcTemplate);
        return 1;
    }


    @Override
    public int updatePerson(UUID id, Person person) {
        final String sql = "UPDATE person SET name=?, email=?, password=? WHERE id=?";
        return jdbcTemplate.update(sql, person.getName(), person.getEmail(), person.getPassword(), id.toString());
    }


    @Override
    public Optional<Person> selectPersonbyId(UUID id) {
        final String sql = "SELECT * FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            return new Person(id, name, email, password, null);
        });
        return Optional.ofNullable(person);
    }



    @Override
    public Optional<Person> selectPersonbyName(String name) {
        final String sql = "SELECT * FROM person WHERE name = ?";
        try {
            Person person = jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> {
                UUID id = UUID.fromString(rs.getString("id"));
                String personName = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new Person(id, personName, email, password , null);
            });
            return Optional.ofNullable(person);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> selectPersonbyEmail(String email){
        return DB.stream().filter(person -> person.getEmail().equals(email)).findFirst();
    }

    @Override
    public int addLocation(UUID personId, Triple<Integer, Integer, Color> location) {
        final String locationSql = "INSERT INTO locations (location_id, id, x, y) VALUES (?, ?, ?, ?)";
        final String colorSql = "INSERT INTO colors (color_id, location_id, red, green, blue, transparency) VALUES (?, ?, ?, ?, ?, ?)";

        int x = location.a;
        int y = location.b;
        String locationId = UUID.randomUUID().toString();
        int rowsAffected = jdbcTemplate.update(locationSql, locationId, personId.toString(), x, y);

        if (rowsAffected > 0) {
            String colorId = UUID.randomUUID().toString();
            Color color = location.c;
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            float transparency = color.getTransparency();

            jdbcTemplate.update(colorSql, colorId, locationId, red, green, blue, transparency);
        }

        return rowsAffected;
    }
    @Override
    public List<Locations> selectAllLocations() {
        final String sql = "SELECT * FROM locations";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UUID locationId = UUID.fromString(rs.getString("location_id"));
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            Color color = new Color(rs.getInt("red"), rs.getInt("green"), rs.getInt("blue"), rs.getInt("transparency"));
            return new Locations(locationId, x, y, color);
        });
    }




    @Override
    public Optional<Triple<Integer, Integer, Color>> getLocation(UUID personId) {
        final String sql = "SELECT l.x, l.y, c.red, c.green, c.blue, c.transparency FROM locations l " +
                "INNER JOIN colors c ON l.location_id = c.location_id " +
                "WHERE l.id = ?";
        List<Object[]> rows = jdbcTemplate.query(sql, new Object[]{personId.toString()}, (rs, rowNum) -> {
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            int red = rs.getInt("red");
            int green = rs.getInt("green");
            int blue = rs.getInt("blue");
            float transparency = rs.getFloat("transparency");
            Color color = new Color(red, green, blue, (int)(transparency * 255));
            return new Object[]{x, y, color};
        });
        if(rows.size() > 0) {
            Object[] row = rows.get(0);
            int x = (int)row[0];
            int y = (int)row[1];
            Color color = (Color)row[2];
            return Optional.of(new Triple<>(x, y, color));
        } else {
            return Optional.empty();
        }
    }
    @Override
    public int updateLocation(UUID locationId, Triple<Integer, Integer, Color> location) {
        final String locationSql = "UPDATE locations SET x=?, y=? WHERE location_id=?";
        final String colorSql = "UPDATE colors SET red=?, green=?, blue=?, transparency=? WHERE location_id=?";

        int x = location.a;
        int y = location.b;
        float transparency = location.c.getAlpha() / 255.0f;
        int red = location.c.getRed();
        int green = location.c.getGreen();
        int blue = location.c.getBlue();

        int updatedRows = jdbcTemplate.update(locationSql, x, y, locationId.toString());
        jdbcTemplate.update(colorSql, red, green, blue, transparency, locationId.toString());

        return updatedRows;
    }




}
