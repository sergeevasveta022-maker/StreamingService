package com.example.streaming_service.controller;

import com.example.streaming_service.entity.Film;
import com.example.streaming_service.repository.FilmRepository;
import com.example.streaming_service.service.FilmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/films")
public class FilmController {

    private final FilmService filmService;
    private final FilmRepository filmRepository;

    public FilmController(FilmService filmService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    @PostMapping
    public String fetchFilms(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer ratingFrom,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer ratingTo,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String keyword
    ) {
        filmService.fetchAndSaveFilms(page, ratingFrom, yearFrom, ratingTo, yearTo, order,type,keyword);
        return "Films saved";
    }

    @GetMapping
    public Page<Film> findFilms(
            @RequestParam(required = false) String filmName,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(required = false) Float ratingFrom,
            @RequestParam(required = false) Float ratingTo,
            Pageable pageable
    ) {
        return filmRepository.search(filmName, yearFrom, yearTo, ratingFrom, ratingTo, pageable);
    }
}
