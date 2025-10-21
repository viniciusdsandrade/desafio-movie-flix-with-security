package com.devsuperior.movieflix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String text;

    @NotNull(message = "Campo requerido")
    private Long movieId;    
    
    private Long userId;
    private String userName;
    private String userEmail;
}
