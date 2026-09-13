package com.example.streaming_service.report;

import java.util.UUID;

public class ReportRequestedEvent {

    private UUID reportId;

    public ReportRequestedEvent(UUID reportId) {
        this.reportId = reportId;
    }

    public ReportRequestedEvent() {
    }

    public UUID getReportId() {
        return reportId;
    }

    public void setReportId(UUID reportId) {
        this.reportId = reportId;
    }
}
