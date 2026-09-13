package com.example.streaming_service.report;

import com.example.streaming_service.entity.Film;
import com.example.streaming_service.entity.Report;
import com.example.streaming_service.exception.ReportNotFoundException;
import com.example.streaming_service.repository.FilmRepository;
import com.example.streaming_service.repository.ReportRepository;
import com.example.streaming_service.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.mail.MessagingException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {
    private final FilmRepository filmRepository;
    private final EmailService emailService;
    private final ReportRepository reportRepository;
    private final ReportEventProducer reportEventProducer;

    public ReportService(FilmRepository filmRepository, EmailService emailService, ReportRepository reportRepository, ReportEventProducer reportEventProducer) {
        this.filmRepository = filmRepository;
        this.emailService = emailService;
        this.reportRepository = reportRepository;
        this.reportEventProducer = reportEventProducer;
    }

    public void sendReport(String email) throws MessagingException, JsonProcessingException, IOException {
        List<Film> allFilms = new ArrayList<>();
        StringWriter csvWriter = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("id", "filmId", "filmName", "year", "rating", "description")
                .build();

        int pageNumber = 0;
        int pageSize = 500;
        Page<Film> page;
        try (CSVPrinter printer = new CSVPrinter(csvWriter, format)) {
            do {
                page = filmRepository.findAll(PageRequest.of(pageNumber, pageSize));
                for (Film film : page.getContent()) {
                    printer.printRecord(film.getId(), film.getFilmId(), film.getFilmName(),
                            film.getYear(), film.getRating(), film.getDescription());
                    allFilms.add(film);
                }
                pageNumber++;
            } while (page.hasNext());
        }
        String csv = csvWriter.toString();
        String xml = generateXml(allFilms);
        emailService.sendReport(email, csv, xml);
    }

    public String generateCsv(List<Film> films) throws IOException {
        StringWriter writer = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("id", "filmId", "filmName", "year", "rating", "description")
                .build();
        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            for (Film film : films) {
                printer.printRecord(film.getId(), film.getFilmId(), film.getFilmName(),
                        film.getYear(), film.getRating(), film.getDescription());
            }
        }
        return writer.toString();
    }

    public Report createReport(String email) {
        Report report = new Report();
        report.setReportId(UUID.randomUUID());
        report.setEmail(email);
        report.setStatus(ReportStatus.CREATED);
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        Report saved = reportRepository.save(report);
        reportEventProducer.publishReportRequested(saved.getReportId());
        return saved;
    }

   public String generateXml(List<Film> films) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        FilmsXmlWrapper wrapper = new FilmsXmlWrapper(films);
        return xmlMapper.writeValueAsString(wrapper);
    }

    public Report getReportByReportId(UUID reportId) {
        return reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new ReportNotFoundException("Отчёт не найден: " + reportId));
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public void processReport(UUID reportId) {
        Report report = getReportByReportId(reportId);
        report.setStatus(ReportStatus.PROCESSING);
        report.setUpdatedAt(LocalDateTime.now());
        reportRepository.save(report);

        try {
            List<Film> allFilms = new ArrayList<>();
            StringWriter csvWriter = new StringWriter();
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader("id", "filmId", "filmName", "year", "rating", "description")
                    .build();

            int pageNumber = 0;
            int pageSize = 500;
            Page<Film> page;
            try (CSVPrinter printer = new CSVPrinter(csvWriter, format)) {
                do {
                    page = filmRepository.findAll(PageRequest.of(pageNumber, pageSize));
                    for (Film film : page.getContent()) {
                        printer.printRecord(film.getId(), film.getFilmId(), film.getFilmName(),
                                film.getYear(), film.getRating(), film.getDescription());
                        allFilms.add(film);
                    }
                    pageNumber++;
                } while (page.hasNext());
            }
            String csv = csvWriter.toString();
            String xml = generateXml(allFilms);
            emailService.sendReport(report.getEmail(), csv, xml);

            report.setStatus(ReportStatus.COMPLETED);
            report.setUpdatedAt(LocalDateTime.now());
            reportRepository.save(report);
        } catch (Exception e) {
            report.setStatus(ReportStatus.FAILED);
            report.setErrorMessage(e.getMessage());
            report.setUpdatedAt(LocalDateTime.now());
            reportRepository.save(report);
            throw new RuntimeException("Не удалось сформировать отчёт " + reportId, e);
        }
    }
}
