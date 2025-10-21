package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    private final GenreService genreService;
    public GenreController(GenreService genreService) { this.genreService = genreService; }

    @PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenreDTO>> findAll() {
        return ok(genreService.findAll());
    }
}

