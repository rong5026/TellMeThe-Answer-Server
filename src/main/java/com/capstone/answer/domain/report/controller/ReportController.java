package com.capstone.answer.domain.report.controller;


import com.capstone.answer.domain.report.dto.ReportAddDto;
import com.capstone.answer.domain.report.dto.ReportUpdateDto;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/report", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReportController {

    private final ReportService reportService;

    // 신고 추가
    @PostMapping("/add")
    public ResponseEntity<Object> addReport(@RequestBody ReportAddDto reportAddDto) {

        Map<String, Object> response = new HashMap<>();
        Report result = reportService.add(reportAddDto);

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

    // 신고 업데이트
    @PostMapping("/update")
    public ResponseEntity<Object> updateReport(@RequestBody ReportUpdateDto reportUpdateDto) {
        Map<String, Object> response = new HashMap<>();
        boolean result = reportService.update(reportUpdateDto);

        if(result) {
            response.put("result", true);
            response.put("message", "Update Success");
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Update Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }

    // 신고 삭제
    @DeleteMapping("/delete/{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("reportId") Long reportId){
        Map<String, Object> response = new HashMap<>();
        boolean result = reportService.delete(reportId);

        if(result) {
            response.put("result", true);
            response.put("message", "Delete Success");
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Delete Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }




}
