package com.devsuperior.movieflix.services.impl;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.movie.MovieCardDTO;
import com.devsuperior.movieflix.dto.movie.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Movie entity = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie id " + id + " not found"));
        return toDetailsDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAllPaged(Long genreId, Pageable pageable) {
        Page<Movie> page;
        if (genreId == null) {
            page = movieRepository.findAll(pageable);
        } else {
            // Exigir no repository:
            // Page<Movie> findByGenreId(Long genreId, Pageable pageable);
            page = movieRepository.findByGenreId(genreId, pageable);
        }
        return page.map(this::toCardDto);
    }

    private MovieCardDTO toCardDto(Movie entity) {
        MovieCardDTO dto = new MovieCardDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setSubTitle(entity.getSubTitle());
        dto.setYear(entity.getYear());
        dto.setImgUrl(entity.getImgUrl());
        return dto;
    }

    private MovieDetailsDTO toDetailsDto(Movie entity) {
        MovieDetailsDTO dto = new MovieDetailsDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setSubTitle(entity.getSubTitle());
        dto.setYear(entity.getYear());
        dto.setImgUrl(entity.getImgUrl());
        dto.setSynopsis(entity.getSynopsis());
        if (entity.getGenre() != null) {
            GenreDTO g = new GenreDTO();
            g.setId(entity.getGenre().getId());
            g.setName(entity.getGenre().getName());
            dto.setGenre(g);
        }
        return dto;
    }
}
