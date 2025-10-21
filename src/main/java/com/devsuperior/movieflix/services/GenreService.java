package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenreService {
    @Transactional(readOnly = true)
    List<GenreDTO> findAll();
}
