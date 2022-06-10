package com.backend.airline_tickets_agency_management.model.service.report;

import com.backend.airline_tickets_agency_management.model.dto.report.IReportDto;

import java.util.List;

public interface IReportService {
    List<IReportDto>getAll();
    List<IReportDto>getListStatisticalOneDate(String startDate, String endDate);
    List<IReportDto>getTop5Employee(String startDate, String endDate);
    List<IReportDto>getTop5Airline(String startDate, String endDate);
}
