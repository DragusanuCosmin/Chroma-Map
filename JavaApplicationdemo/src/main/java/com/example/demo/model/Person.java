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

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

}
