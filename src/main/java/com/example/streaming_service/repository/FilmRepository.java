package com.example.streaming_service.repository;

import com.example.streaming_service.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

public interface FilmRepository extends JpaRepository<Film, Long> {

boolean existsByFilmId(Integer filmId);

    @Query("SELECT f FROM Film f WHERE " +
            "(:filmName IS NULL OR f.filmName LIKE %:filmName%) AND " +
            "(:yearFrom IS NULL OR f.year >= :yearFrom) AND " +
            "(:yearTo IS NULL OR f.year <= :yearTo) AND " +
            "(:ratingFrom IS NULL OR f.rating >= :ratingFrom) AND " +
            "(:ratingTo IS NULL OR f.rating <= :ratingTo)")
    Page<Film> search(@Param("filmName") String filmName,
                      @Param("yearFrom") Integer yearFrom,
                      @Param("yearTo") Integer yearTo,
                      @Param("ratingFrom") Float ratingFrom,
                      @Param("ratingTo") Float ratingTo,
                      Pageable pageable);

}
