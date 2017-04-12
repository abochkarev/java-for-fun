package com.mockito.ttd;


import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

interface IReportGenerator {
    void generateReport(ReportEntity reportEntity);
}

class ReportEntity {
    private Long reportId;
    private Date startDate;
    private Date endDate;
    private byte[] content;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}

class ReportGeneratorService {
    private IReportGenerator reportGenerator;

    public void generateReport(Date startDate, Date endDate, byte[] content) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setContent(content);
        reportEntity.setStartDate(startDate);
        reportEntity.setEndDate(endDate);
        reportGenerator.generateReport(reportEntity);
    }
}

public class ReportServiceTest {

    @InjectMocks
    private ReportGeneratorService reportGeneratorService;

    @Mock
    private IReportGenerator reportGenerator;

    @Captor
    private ArgumentCaptor<ReportEntity> reportCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 11, 25);
        Calendar endDate = Calendar.getInstance();
        endDate.set(9999, 12, 31);
        String reportContent = "Report Content";
        reportGeneratorService.generateReport(startDate.getTime(), endDate.getTime(), reportContent.getBytes());
        Mockito.verify(reportGenerator).generateReport(reportCaptor.capture());
        ReportEntity report = reportCaptor.getValue();
        assertEquals(116, report.getStartDate().getYear());
        assertEquals(11, report.getStartDate().getMonth());
        assertEquals(25, report.getStartDate().getDate());
        assertEquals(8100, report.getEndDate().getYear());
        assertEquals(0, report.getEndDate().getMonth());
        assertEquals(31, report.getEndDate().getDate());
        assertEquals("Report Content", new String(report.getContent()));
    }

}
