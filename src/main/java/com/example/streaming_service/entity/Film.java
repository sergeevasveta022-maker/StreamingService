package com.example.streaming_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Column(name = "film_id")
    Integer filmId;

    @Column(name = "film_name")
    String filmName;

    @Column(name = "year")
    Integer year;

    @Column(name = "rating")
    Float rating;

    @Column(name = "description")
    String description;

    public Film(Long id, Integer filmId, String filmName, Integer year, Float rating, String description) {
        Id = id;
        this.filmId = filmId;
        this.filmName = filmName;
        this.year = year;
        this.rating = rating;
        this.description = description;
    }

    public Film() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "Id=" + Id +
                ", filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
