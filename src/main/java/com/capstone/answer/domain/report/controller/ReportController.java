package com.capstone.answer.domain.report.controller;


import com.capstone.answer.domain.report.dto.ReportAddDto;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.service.ReportService;
import com.capstone.answer.domain.report.service.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
//@RequestMapping(value = "/record", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReportController {

    private final ReportService reportService;

    // 신고 추가
    @PostMapping("/record")
    public ResponseEntity<Object> addReport(@RequestBody ReportAddDto reportAddDto) {

        Map<String, Object> response = new HashMap<>();

        Report result = reportService.addReport(reportAddDto);

        if(result != null) {
            response.put("result", true);
            response.put("message", "Registeration Success");
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Registeration Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }
}
