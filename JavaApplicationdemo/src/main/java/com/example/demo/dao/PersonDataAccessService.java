package com.example.demo.dao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("personDao")
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
    public int addPerson(Person person) {
        final String sql = "INSERT INTO person ( id , name, email, password) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,UUID.randomUUID().toString(), person.getName(), person.getEmail(), person.getPassword());
    }


    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UUID id=UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            return new Person(id,name,email,password);
        });
    }


    @Override
    public int deletePersonbyId(UUID id) {
        final String sql = "DELETE FROM person WHERE id= ?";
        jdbcTemplate.update(sql, id.toString());
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
        try {
            Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                String personName = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new Person(id, personName, email, password);
            });
            return Optional.ofNullable(person);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
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
                return new Person(id, personName, email, password);
            });
            return Optional.ofNullable(person);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> selectPersonbyEmail(String email){
        final String sql = "SELECT * FROM person WHERE name = ?";
        try {
            Person person = jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("name");
                String personemail = rs.getString("email");
                String password = rs.getString("password");
                return new Person(id, name, personemail, password);
            });
            return Optional.ofNullable(person);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }





}
