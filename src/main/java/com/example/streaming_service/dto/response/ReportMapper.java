package com.example.streaming_service.dto.response;

import com.example.streaming_service.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportResponseDto toDto(Report report) {
        ReportResponseDto dto = new ReportResponseDto();
        dto.setReportId(report.getReportId());
        dto.setEmail(report.getEmail());
        dto.setStatus(report.getStatus());
        dto.setCreatedAt(report.getCreatedAt());
        dto.setUpdatedAt(report.getUpdatedAt());
        dto.setErrorMessage(report.getErrorMessage());
        return dto;
    }
}
