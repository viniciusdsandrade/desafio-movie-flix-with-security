package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/movies")
class MovieController {

    private final MovieService movieService;
    public MovieController(MovieService movieService) { this.movieService = movieService; }

    // 401 token inválido; 200 para VISITOR/MEMBER; 404 se id não existir (lançado no service)
    @PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        return ok(movieService.findById(id));
    }

    // 401 token inválido; 200 página ordenada por título; filtra por genreId quando presente
    @PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<MovieCardDTO>> findAll(
            @RequestParam(value = "genreId", required = false) Long genreId,
            @PageableDefault(sort = "title", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ok(movieService.findAllPaged(genreId, pageable));
    }
}
