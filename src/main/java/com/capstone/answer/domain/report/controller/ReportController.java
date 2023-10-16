package com.capstone.answer.domain.report.controller;


import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.service.ReportService;
import com.capstone.answer.domain.report.service.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/record", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReportController {

    private final ReportServiceImpl reportService;

    // 신고 추가

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Report addReport(@RequestBody Report report, @RequestParam("email") String memberEmail) {

        System.out.println(memberEmail);
        return reportService.addReport(report, memberEmail);
    }



}
