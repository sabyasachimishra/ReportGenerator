package com.example.ReportGenerator.controllers;

import com.example.ReportGenerator.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reports")
public class ReportController {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @GetMapping("/generate")
    public ResponseEntity<String> generateAndReturnReport() {
        String report = reportGenerationService.generateReport();
        return ResponseEntity.ok(report);
    }
}
