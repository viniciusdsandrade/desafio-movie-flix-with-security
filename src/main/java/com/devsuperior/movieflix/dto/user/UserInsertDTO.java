package com.devsuperior.movieflix.dto.user;

import com.devsuperior.movieflix.validation.annotation.StrongPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

public record UserInsertDTO(
        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 60, message = "First name must be between 2 and 60 characters")
        @Pattern(regexp = "^[\\p{L} .'-]{2,60}$", message = "First name contains invalid characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 120, message = "Email must be at most 120 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 60, message = "Password must be between 6 and 60 characters")
        @JsonProperty(access = WRITE_ONLY)
        @StrongPassword()
        String password
) {
}

