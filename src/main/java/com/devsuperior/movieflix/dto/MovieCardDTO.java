package com.devsuperior.movieflix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCardDTO {

    private Long id;
    private String title;
    private String subTitle;
    private Integer year;
    private String imgUrl;
}
