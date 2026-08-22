package com.example.streaming_service.report;

import com.example.streaming_service.entity.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

   public String generateCsv(List<Film> films){
StringBuilder sb = new StringBuilder();
sb.append("id, filmId, filmName, year, rating,description\n");
        for (Film film:films) {
            sb.append(film.getId() + "," + film.getFilmId() + ",\"" + film.getFilmName() + "\"," + film.getYear() + "," + film.getRating() + ",\"" + film.getDescription() + "\"\n");
        }
        return sb.toString();
    }

   public String generateXml(List<Film> films) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        FilmsXmlWrapper wrapper = new FilmsXmlWrapper(films);
        return xmlMapper.writeValueAsString(wrapper);
    }
}
