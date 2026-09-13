package com.example.streaming_service.controller;

import com.example.streaming_service.dto.response.ReportMapper;
import com.example.streaming_service.dto.response.ReportResponseDto;
import com.example.streaming_service.entity.Report;
import com.example.streaming_service.report.ReportService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v2/reports")
public class ReportController {

    private final ReportService reportService;
    private final ReportMapper reportMapper;

    public ReportController(ReportService reportService, ReportMapper reportMapper) {
        this.reportService = reportService;
        this.reportMapper = reportMapper;
    }

    @PostMapping("/report")
    public String sendReport(@RequestParam @Email String email) throws MessagingException, IOException {
        reportService.sendReport(email);
        return "Report sent";
    }

    @PostMapping
    public ResponseEntity<ReportResponseDto> createReport(@RequestParam @Email String email) {
        Report report = reportService.createReport(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reportMapper.toDto(report));
    }

    @GetMapping("/{reportId}")
    public ReportResponseDto getReport(@PathVariable UUID reportId) {
        Report report = reportService.getReportByReportId(reportId);
        return reportMapper.toDto(report);
    }

    @GetMapping
    public List<ReportResponseDto> getAllReports() {
        return reportService.getAllReports().stream()
                .map(reportMapper::toDto)
                .toList();
    }
}
