package com.devsuperior.movieflix.services;


import com.devsuperior.movieflix.dto.user.UserDTO;
import com.devsuperior.movieflix.dto.user.UserInsertDTO;
import com.devsuperior.movieflix.dto.user.UserUpdateDTO;
import com.devsuperior.movieflix.entities.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> findAllPaged(Pageable pageable);

    @Transactional
    UserDTO insert(@Valid UserInsertDTO userInsertDTO);

    UserDTO findById(Long id);

    @Transactional
    UserDTO update(Long id, @Valid UserUpdateDTO userInsertDTO);

    User authenticated();

    UserDTO getMe();
}
