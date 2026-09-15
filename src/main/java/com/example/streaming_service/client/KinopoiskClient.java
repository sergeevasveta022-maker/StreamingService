package com.example.streaming_service.client;

import com.example.streaming_service.dto.*;
import com.example.streaming_service.exception.KinopoiskUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

@Component
public class KinopoiskClient {

    @Value("${kinopoisk.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    @Value("${kinopoisk.api.token}")
    private String apiToken;

    @Value("${kinopoisk.api.host}")
    private String apiHost;

    public KinopoiskClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @TimeLimiter(name = "kinopoisk")
    @RateLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    public CompletableFuture<StreamingSearchResponse> searchFilms(Integer page, Integer ratingFrom, Integer yearFrom, Integer ratingTo, Integer yearTo, String order, String type, String keyword) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);

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

            ResponseEntity<StreamingSearchResponse> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingSearchResponse.class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить список фильмов с Кинопоиска", e);
            }

            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ");
            }

            return response.getBody();
        });
    }

    @TimeLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    @Cacheable(value = "filmDetails", key = "#filmId")
    public CompletableFuture<StreamingFilmDetails> getFilmDetails(Integer filmId) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = baseUrl + "/" + filmId;

            ResponseEntity<StreamingFilmDetails> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingFilmDetails.class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить детали фильма.", e);
            }
            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ.");
            }
            return response.getBody();
        });
    }

    @TimeLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    public CompletableFuture<StreamingSimilarFilmsResponse> getSimilarFilms(Integer filmId) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = apiHost + "/api/v2.2/films/" + filmId + "/similars";

            ResponseEntity<StreamingSimilarFilmsResponse> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingSimilarFilmsResponse.class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить похожие фильмы.", e);
            }
            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ.");
            }
            return response.getBody();
        });
    }

    @TimeLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    public CompletableFuture<StreamingStaffItem[]> getStaff(Integer filmId) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = apiHost + "/api/v1/staff?filmId=" + filmId;

            ResponseEntity<StreamingStaffItem[]> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingStaffItem[].class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить актерский состав.", e);
            }
            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ.");
            }
            return response.getBody();
        });
    }

    @TimeLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    public CompletableFuture<StreamingReviewsResponse> getReview(Integer filmId) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = apiHost + "/api/v2.2/films/" + filmId + "/reviews";

            ResponseEntity<StreamingReviewsResponse> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingReviewsResponse.class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить ревью фильма.", e);
            }
            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ.");
            }
            return response.getBody();
        });
    }

    @TimeLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    public CompletableFuture<StreamingSeasonsResponse> getSeasons(Integer filmId) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = apiHost + "/api/v2.2/films/" + filmId + "/seasons";

            ResponseEntity<StreamingSeasonsResponse> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingSeasonsResponse.class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить сезоны фильма.", e);
            }
            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ.");
            }
            return response.getBody();
        });
    }

    @TimeLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    public CompletableFuture<StreamingImagesResponse> getImages(Integer filmId) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = apiHost + "/api/v2.2/films/" + filmId + "/images";

            ResponseEntity<StreamingImagesResponse> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingImagesResponse.class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить постер фильма.", e);
            }
            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ.");
            }
            return response.getBody();
        });
    }

    @TimeLimiter(name = "kinopoisk")
    @CircuitBreaker(name = "kinopoisk")
    @Retry(name = "kinopoisk")
    public CompletableFuture<StreamingPremieresResponse> getPremieres(Integer year, String month) {
        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiToken);
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = UriComponentsBuilder.fromUriString(apiHost + "/api/v2.2/films/premieres")
                    .queryParam("year", year)
                    .queryParam("month", month)
                    .toUriString();

            ResponseEntity<StreamingPremieresResponse> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, entity, StreamingPremieresResponse.class);
            } catch (RestClientException e) {
                throw new KinopoiskUnavailableException("Не удалось получить премьеры.", e);
            }
            if (response.getBody() == null) {
                throw new KinopoiskUnavailableException("Кинопоиск вернул пустой ответ.");
            }
            return response.getBody();
        });
    }
}
