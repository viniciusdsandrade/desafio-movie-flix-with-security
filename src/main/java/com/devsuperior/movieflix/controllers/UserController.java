package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.user.UserInsertDTO;
import com.devsuperior.movieflix.dto.user.UserUpdateDTO;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.movieflix.dto.user.UserDTO;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.created;


@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO me = userService.getMe();
        return ok(me);
    }

    @PermitAll
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = userService.findAllPaged(pageable);
        return ok(users);
    }

    @PermitAll
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ok(user);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<UserDTO> createUser(
            @Valid @RequestBody UserInsertDTO userInsertDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        UserDTO createdUser = userService.insert(userInsertDTO);
        URI uri = uriComponentsBuilder
                .path("/users/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return created(uri).body(createdUser);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDTO userInsertDTO
    ) {
        UserDTO updatedUser = userService.update(id, userInsertDTO);
        return ok(updatedUser);
    }
}
