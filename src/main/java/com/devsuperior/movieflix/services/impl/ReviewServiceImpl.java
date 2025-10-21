package com.devsuperior.movieflix.services.impl;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            MovieRepository movieRepository,
            UserRepository userRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        // Valida existência do filme (getReferenceById lança EntityNotFoundException ao acessar uma propriedade se não existir)
        Movie movie;
        try {
            movie = movieRepository.getReferenceById(dto.getMovieId());
            movie.getId(); // força o acesso para disparar EntityNotFoundException se inexistente
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Movie id " + dto.getMovieId() + " not found");
        }

        User user = authenticatedUser();

        Review entity = new Review();
        entity.setText(dto.getText());
        entity.setMovie(movie);
        entity.setUser(user);

        entity = reviewRepository.save(entity);

        ReviewDTO out = new ReviewDTO();
        out.setId(entity.getId());
        out.setText(entity.getText());
        out.setMovieId(entity.getMovie().getId());
        out.setUserId(user.getId());
        out.setUserName(user.getName());
        out.setUserEmail(user.getEmail());
        return out;
    }

    private User authenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : null;
        if (username == null) throw new UsernameNotFoundException("Authenticated user not found");
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
