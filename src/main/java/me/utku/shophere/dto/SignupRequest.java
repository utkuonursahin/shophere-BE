package me.utku.shophere.dto;

import java.time.Instant;

public record SignupRequest(String name, String surname, String email, String password, Instant birthDate) {
}
