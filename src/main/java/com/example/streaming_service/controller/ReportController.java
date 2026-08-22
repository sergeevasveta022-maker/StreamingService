package com.example.streaming_service.controller;

import com.example.streaming_service.entity.Film;
import com.example.streaming_service.report.ReportService;
import com.example.streaming_service.repository.FilmRepository;
import com.example.streaming_service.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/films")
public class ReportController {

    private final FilmRepository filmRepository;
    private final ReportService reportService;
    private final EmailService emailService;

    public ReportController(FilmRepository filmRepository, ReportService reportService, EmailService emailService) {
        this.filmRepository = filmRepository;
        this.reportService = reportService;
        this.emailService = emailService;
    }

    @PostMapping("/report")
    public String sensReport(@RequestParam String email) throws MessagingException, JsonProcessingException {
        var films = filmRepository.findAll();
        String cvs = reportService.generateCsv(films);
        String xml = reportService.generateXml(films);
        emailService.sendReport(email, cvs, xml);
        return "Report sent";
    }
}
