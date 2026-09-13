package com.example.streaming_service.dto.response;

import com.example.streaming_service.entity.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public FilmResponseDto toDto(Film film) {
        FilmResponseDto filmResponseDto = new FilmResponseDto();
        filmResponseDto.setId(film.getId());
        filmResponseDto.setFilmId(film.getFilmId());
        filmResponseDto.setFilmName(film.getFilmName());
        filmResponseDto.setYear(film.getYear());
        filmResponseDto.setRating(film.getRating());
        filmResponseDto.setDescription(film.getDescription());
        return filmResponseDto;
    }
}
