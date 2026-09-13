package com.example.streaming_service.report;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReportEventProducer {

    private static final String TOPIC = "report.requested";

    private final KafkaTemplate<String, ReportRequestedEvent> kafkaTemplate;

    public ReportEventProducer(KafkaTemplate<String, ReportRequestedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishReportRequested(UUID reportId) {
        kafkaTemplate.send(TOPIC, reportId.toString(), new ReportRequestedEvent(reportId));
    }
}
