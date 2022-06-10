package com.backend.airline_tickets_agency_management.controller;

import com.backend.airline_tickets_agency_management.model.dto.report.IReportDto;
import com.backend.airline_tickets_agency_management.model.service.report.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("api/reports")
public class ReportController {
    @Autowired
    IReportService reportService;

    @GetMapping("/get-all")
    public ResponseEntity<List<IReportDto>> getAll() {
        List<IReportDto> reports = reportService.getAll();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/get-statistical-one-date")
    public ResponseEntity<List<IReportDto>> getListStatisticalOneDate(@RequestParam(defaultValue = "") String startDate,
                                                                      @RequestParam(defaultValue = "") String endDate) {
        List<IReportDto> reportDtoList = reportService.getListStatisticalOneDate(startDate, endDate);
        if (reportDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reportDtoList, HttpStatus.OK);
    }

    @GetMapping("/get-top-5-employee")
    public ResponseEntity<List<IReportDto>> getTop5Employee(@RequestParam(defaultValue = "") String startDate,
                                                            @RequestParam(defaultValue = "") String endDate) {
        List<IReportDto> reportDtoList = reportService.getTop5Employee(startDate, endDate);
        if (reportDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reportDtoList, HttpStatus.OK);
    }
    @GetMapping("/get-top-5-airline")
    public ResponseEntity<List<IReportDto>> getTop5Airline(@RequestParam(defaultValue = "") String startDate,
                                                            @RequestParam(defaultValue = "") String endDate) {
        List<IReportDto> reportDtoList = reportService.getTop5Airline(startDate, endDate);
        if (reportDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reportDtoList, HttpStatus.OK);
    }
}
