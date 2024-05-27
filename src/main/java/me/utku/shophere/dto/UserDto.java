package me.utku.shophere.dto;

import java.time.Instant;

public record UserDto(String name, String surname, String email, Instant birthDate) {
}
