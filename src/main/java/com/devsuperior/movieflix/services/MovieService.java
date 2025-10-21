package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface MovieService {

    @Transactional(readOnly = true)
    MovieDetailsDTO findById(Long id);

    @Transactional(readOnly = true)
    Page<MovieCardDTO> findAllPaged(Long genreId, Pageable pageable);
}
