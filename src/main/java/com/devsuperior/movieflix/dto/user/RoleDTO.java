package com.devsuperior.movieflix.dto.user;

import com.devsuperior.movieflix.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {

    @JsonProperty(access = WRITE_ONLY)
    private Long id;
    private String authority;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }
}
