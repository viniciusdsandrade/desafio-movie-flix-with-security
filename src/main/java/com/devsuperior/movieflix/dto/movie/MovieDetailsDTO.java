package com.devsuperior.movieflix.dto.movie;

import com.devsuperior.movieflix.dto.GenreDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsDTO {

    private Long id;
    private String title;
    private String subTitle;
    private Integer year;
    private String imgUrl;
    private String synopsis;
    private GenreDTO genre;
}
