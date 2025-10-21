package com.devsuperior.movieflix.services.impl;


import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));

        if (!isAdmin && (me == null || me.getId() == null || !me.getId().equals(userId))) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
