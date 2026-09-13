package com.example.streaming_service.service;

import com.example.streaming_service.client.KinopoiskClient;
import com.example.streaming_service.dto.StreamingFilmItem;
import com.example.streaming_service.dto.response.FilmMapper;
import com.example.streaming_service.dto.response.FilmResponseDto;
import com.example.streaming_service.entity.Film;
import com.example.streaming_service.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private final KinopoiskClient kinopoiskClient;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public FilmService(KinopoiskClient kinopoiskClient, FilmRepository filmRepository, FilmMapper filmMapper) {
        this.kinopoiskClient = kinopoiskClient;
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public void fetchAndSaveFilms(Integer page, Integer ratingFrom, Integer yearFrom, Integer ratingTo, Integer yearTo, String order, String type, String keyword) {
        var searchResponce = kinopoiskClient.searchFilms(page, ratingFrom, yearFrom, ratingTo, yearTo, order, type, keyword);

        for (StreamingFilmItem item : searchResponce.getItems()) {
            boolean exists = filmRepository.existsByFilmId(item.getStreamingId());
            if (!exists) {
                var details = kinopoiskClient.getFilmDetails(item.getStreamingId());

                Film film = new Film();
                film.setFilmId(item.getStreamingId());
                film.setFilmName(item.getNameRu());
                film.setYear(item.getYear());
                film.setRating(item.getRatingStreaming().floatValue());
                film.setDescription(details.getDescription());

                filmRepository.save(film);
            }
        }
    }

    public Page<FilmResponseDto> searchFilms(String filmName, Integer yearFrom, Integer yearTo, Float ratingFrom, Float ratingTo, Pageable pageable){
        Page<Film> films = filmRepository.search(filmName, yearFrom, yearTo, ratingFrom, ratingTo,pageable);
        return films.map(filmMapper::toDto);
    }


}
