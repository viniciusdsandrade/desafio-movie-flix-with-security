package com.devsuperior.movieflix.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Size(min = 2, max = 60)
        @Pattern(regexp = "^[\\p{L} .'-]{2,60}$")
        String name,

        @Email(message = "Email must be valid")
        @Size(max = 120)
        String email
) { }
