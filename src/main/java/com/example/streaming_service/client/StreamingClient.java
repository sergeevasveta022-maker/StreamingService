package com.example.streaming_service.client;

import com.example.streaming_service.dto.StreamingFilmDetails;
import com.example.streaming_service.dto.StreamingSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StreamingClient {

    private static final String BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films";

    private final RestTemplate restTemplate;

    @Value("${kinopoisk.api.token}")
    private String apiToken;

    public StreamingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StreamingSearchResponse searchFilms(Integer page, Integer ratingFrom, Integer yearFrom, Integer ratingTo, Integer yearTo, String order, String type, String keyword) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiToken);
        headers.set("Content-Type", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

        if (page != null) {
            builder.queryParam("page", page);
        }
        if (ratingFrom != null) {
            builder.queryParam("ratingFrom", ratingFrom);
        }
        if (yearFrom != null) {
            builder.queryParam("yearFrom", yearFrom);
        }
        if (ratingTo != null) {
            builder.queryParam("ratingTo", ratingTo);
        }
        if (yearTo != null) {
            builder.queryParam("yearTo", yearTo);
        }
        if (order != null) {
            builder.queryParam("order", order);
        }
        if (type != null) {
            builder.queryParam("type", type);
        }
        if (keyword != null) {
            builder.queryParam("keyword", keyword);
        }

        String url = builder.toUriString();

        ResponseEntity<StreamingSearchResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, StreamingSearchResponse.class);
        return response.getBody();
    }

    public StreamingFilmDetails getFilmDetails(Integer filmId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = BASE_URL + "/" + filmId;

        ResponseEntity<StreamingFilmDetails> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, StreamingFilmDetails.class);
        return response.getBody();
    }
}
