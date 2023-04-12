package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class Locations {
    private final UUID location_id;
    @NotBlank
    private final int x;
    @NotBlank
    private final int y;
    @NotBlank
    private final String color;

    public Locations(@JsonProperty("location_id") UUID location_id,
                  @JsonProperty("x") int x,
                  @JsonProperty("y") int y,
                  @JsonProperty("color") String color
    ) {
        this.location_id = location_id;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public UUID getLocation_id() {
        return location_id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }
}
