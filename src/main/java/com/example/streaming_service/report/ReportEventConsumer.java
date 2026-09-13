package com.example.streaming_service.report;

import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class ReportEventConsumer {

    private final ReportService reportService;

    public ReportEventConsumer(ReportService reportService) {
        this.reportService = reportService;
    }

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(delay = 2000, multiplier = 2.0),
            dltTopicSuffix = "-dlt"
    )
    @KafkaListener(topics = "report.requested", groupId = "streaming-service")
    public void handleReportRequested(ReportRequestedEvent event) {
        reportService.processReport(event.getReportId());
    }

    @DltHandler
    public void handleDlt(ReportRequestedEvent event) {
        System.out.println("Отчёт " + event.getReportId() + " не удалось обработать после всех попыток, ушёл в DLT");
    }
}