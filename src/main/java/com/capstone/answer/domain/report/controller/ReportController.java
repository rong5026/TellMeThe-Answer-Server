package com.capstone.answer.domain.report.controller;


import com.capstone.answer.domain.report.dto.ReportAddDto;
import com.capstone.answer.domain.report.dto.ReportListDto;
import com.capstone.answer.domain.report.dto.ReportUpdateDto;
import com.capstone.answer.domain.report.dto.Response.ReportListResponse;
import com.capstone.answer.domain.report.service.Report.ReportService;
import com.capstone.answer.global.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "report", description = "신고 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/report", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "신고추가", description = "신고내역 저장 (formdata형식)" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신고추가 성공", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "신고추가 실패", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PostMapping("/add")
    public ResponseEntity<Object> addReport(@ModelAttribute ReportAddDto reportAddDto) throws IOException {

        try {
            reportService.add(reportAddDto);
            return ResponseEntity.ok(new BaseResponse(true, "신고추가 성공."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(false, "신고추가 실패."));
        }
    }

    @Operation(summary = "신고수정", description = "신고내역 제목, 본문, 위치 수정" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신고수정 성공", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "신고수정 실패", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PostMapping("/update")
    public ResponseEntity<Object> updateReport(@ModelAttribute ReportUpdateDto reportUpdateDto) throws IOException {

        try {
            reportService.update(reportUpdateDto);
            return ResponseEntity.ok(new BaseResponse(true, "신고수정 성공."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(false, "신고수정 실패."));
        }
    }

    @Operation(summary = "신고삭제", description = "신고ID로 삭제" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신고삭제 성공", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "신고삭제 실패", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<Object> deleteReport(@PathVariable("reportId") Long reportId){

        try {
            reportService.delete(reportId);
            return ResponseEntity.ok(new BaseResponse(true, "신고삭제 성공."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(false, "신고삭제 실패."));
        }
    }

    @Operation(summary = "유저 신고내역", description = "유저ID로 신고내역 리스트 조회" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신고내역 조회 성공", content = @Content(schema = @Schema(implementation = ReportListResponse.class))),
            @ApiResponse(responseCode = "500", description = "신고내역 조회 실패", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @GetMapping("/list/{memberId}")
    public ResponseEntity<Object> getUserReport(@PathVariable("memberId") Long memberId) {

        try {
            List<ReportListDto> reports = reportService.getReportByUser(memberId);
            return ResponseEntity.ok(new ReportListResponse(true, "신고내역 조회 성공.", reports));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(false, "신고내역 조회 실패."));
        }
    }

    @Operation(summary = "모든 신고내역", description = "전체 신고리스트 조회" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신고내역 조회 성공", content = @Content(schema = @Schema(implementation = ReportListResponse.class))),
            @ApiResponse(responseCode = "500", description = "신고내역 조회 실패", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @GetMapping("/list/all")
    public ResponseEntity<Object> getAllReport()
    {
        try {
            List<ReportListDto> reports = reportService.getAllReport();
            return ResponseEntity.ok(new ReportListResponse(true, "신고내역 조회 성공.", reports));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(false, "신고내역 조회 실패."));
        }
    }
}
