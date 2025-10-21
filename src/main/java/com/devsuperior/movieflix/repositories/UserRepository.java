package com.devsuperior.movieflix.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.projections.UserDetailsProjection;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    @Query(value = """
            SELECT u.email     AS username,
                   u.password  AS password,
                   r.id        AS rolesId,
                   r.authority AS authority
            FROM tb_user u
            JOIN tb_user_role ur ON u.id = ur.user_id
            JOIN tb_role r       ON r.id = ur.role_id
            WHERE u.email = :email
            """, nativeQuery = true)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
