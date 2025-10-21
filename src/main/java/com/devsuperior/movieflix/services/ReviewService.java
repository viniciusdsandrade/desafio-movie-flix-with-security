package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import jakarta.validation.Valid;

public interface ReviewService {
    ReviewDTO insert(@Valid ReviewDTO dto);
}
