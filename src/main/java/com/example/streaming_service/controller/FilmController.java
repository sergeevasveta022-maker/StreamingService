package com.example.streaming_service.controller;

import com.example.streaming_service.dto.response.FilmResponseDto;
import com.example.streaming_service.service.FilmService;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v2/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public String fetchFilms(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) @Min(0) @Max(10) Integer ratingFrom,
            @RequestParam(required = false) @Min(1900) @Max(2030)Integer yearFrom,
            @RequestParam(required = false) @Min(0) @Max(10) Integer ratingTo,
            @RequestParam(required = false) @Min(1900) @Max(2030)Integer yearTo,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String keyword
    ) {
        filmService.fetchAndSaveFilms(page, ratingFrom, yearFrom, ratingTo, yearTo, order,type,keyword);
        return "Films saved";
    }

    @GetMapping
    public Page<FilmResponseDto> findFilms(
            @RequestParam(required = false) String filmName,
            @RequestParam(required = false) @Min(1900) @Max(2030) Integer yearFrom,
            @RequestParam(required = false) @Min(1900) @Max(2030)Integer yearTo,
            @RequestParam(required = false) @DecimalMin("0") @DecimalMax("10")Float ratingFrom,
            @RequestParam(required = false) @DecimalMin("0") @DecimalMax("10") Float ratingTo,
            Pageable pageable
    ) {
        return filmService.searchFilms(filmName, yearFrom, yearTo, ratingFrom, ratingTo, pageable);
    }
}
