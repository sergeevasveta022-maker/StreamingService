package com.example.streaming_service.repository;

import com.example.streaming_service.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByReportId(UUID reportId);
}
