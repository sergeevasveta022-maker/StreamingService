package com.example.streaming_service.service;

import com.example.streaming_service.client.StreamingClient;
import com.example.streaming_service.dto.StreamingFilmItem;
import com.example.streaming_service.entity.Film;
import com.example.streaming_service.repository.FilmRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private final StreamingClient streamingClient;
    private final FilmRepository filmRepository;

    public FilmService(StreamingClient streamingClient, FilmRepository filmRepository) {
        this.streamingClient = streamingClient;
        this.filmRepository = filmRepository;
    }

    public void fetchAndSaveFilms(Integer page, Integer ratingFrom, Integer yearFrom) {
        var searchResponce = streamingClient.searchFilms(page, ratingFrom, yearFrom);

        for (StreamingFilmItem item : searchResponce.getItems()) {
            boolean exists = filmRepository.existsByFilmId(item.getStreamingId());
            if (!exists) {
                var details = streamingClient.getFilmDetails(item.getStreamingId());

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


}
