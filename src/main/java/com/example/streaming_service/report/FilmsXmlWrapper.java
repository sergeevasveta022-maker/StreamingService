package com.example.streaming_service.report;

import com.example.streaming_service.entity.Film;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "films")
public class FilmsXmlWrapper {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "film")
    private List<Film> films;

    public FilmsXmlWrapper(List<Film> films) {
        this.films = films;
    }
}
