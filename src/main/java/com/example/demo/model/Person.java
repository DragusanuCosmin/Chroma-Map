package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {
    private final UUID ID;
    @NotBlank
    private final String name;
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;

    public Person(@JsonProperty("id") UUID ID,
                  @JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("password") String password) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getID() {
        return ID;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
}

    public String getName() {
        return name;
    }
}
