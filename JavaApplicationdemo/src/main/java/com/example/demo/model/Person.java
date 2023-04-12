package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.Triple;

import java.awt.Color;
import java.util.ArrayList;
import java.util.UUID;

public class Person {
    private final UUID id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
    @NotBlank
    private final Locations location;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("password") String password,
                  @JsonProperty("location") Locations location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Locations getLocation() {
        return location;
    }
}
