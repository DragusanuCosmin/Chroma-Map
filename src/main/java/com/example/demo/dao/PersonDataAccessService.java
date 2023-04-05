package com.example.demo.dao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("realDao")
public class PersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();
    private final JdbcTemplate jbdcTemplate;
    @Autowired
    public PersonDataAccessService(JdbcTemplate jbdcTemplate) {
        this.jbdcTemplate = jbdcTemplate;
    }
    @Override
    public  Optional<Person> insertPerson(UUID ID, Person person) {
        final String  sql="select id,name,email,password from person where id=?";
        Person prs= jbdcTemplate.queryForObject(sql,new Object[]{ID}, (rs, rowNum) -> {
            UUID personID=UUID.fromString(rs.getString("id"));
            String name=rs.getString("name");
            String email=rs.getString("email");
            String password=rs.getString("password");
            return new Person(personID,name,email,password);
        });
            return Optional.ofNullable(prs);
    }

    @Override
    public List<Person> selectAllPeople() {
        final String  sql="select * from person";
        return jbdcTemplate.query(sql, (rs, rowNum) -> {
            UUID ID=UUID.fromString(rs.getString("id"));
            String name=rs.getString("name");
            String email=rs.getString("email");
            String password=rs.getString("password");
            return new Person(ID,name,email,password);
        });
    }

    @Override
    public int deletePersonbyId(UUID ID) {
        Optional<Person> person_maybe= selectPersonbyId(ID);
        if(person_maybe.isEmpty())
            return 0;
        DB.remove( person_maybe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID ID, Person person) {
        return selectPersonbyId(ID).map(person1 -> {
            int indexofptodelete = DB.indexOf(person1);
            if(indexofptodelete>=0) {
                DB.set(indexofptodelete, new Person(ID, person.getName(), person.getEmail(), person.getPassword()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public Optional<Person> selectPersonbyId(UUID ID) {
        return DB.stream().filter(person -> person.getID().equals(ID)).findFirst();
    }
}
