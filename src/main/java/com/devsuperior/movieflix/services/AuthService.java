package com.devsuperior.movieflix.services;

public interface AuthService {
    void validateSelfOrAdmin(Long userId);
}
