package com.capstone.answer.domain.report.controller;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.capstone.answer.domain.report.dto.ReportAddDto;
import com.capstone.answer.domain.report.dto.ReportListDto;
import com.capstone.answer.domain.report.dto.ReportUpdateDto;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/report", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReportController {

    private final ReportService reportService;

    /**
     * 신고추가
     */
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

    /**
     * 신고 업데이트
     */
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateReport(@RequestBody ReportUpdateDto reportUpdateDto) {
        boolean result = reportService.update(reportUpdateDto);
        return createResponse(result, "Update Success", "Update Fail");
    }

    /**
     * 신고 삭제
     */
    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<Map<String, Object>> deleteReport(@PathVariable("reportId") Long reportId){
        boolean result = reportService.delete(reportId);
        return createResponse(result, "Delete Success", "Delete Fail");
    }

    /**
     * 유저에 대한 신고내역
     */
    @GetMapping("/list/{memberId}")
    public ResponseEntity<Map<String, Object>> getUserReport(@PathVariable("memberId") Long memberId) {
        Map<String, Object> response = new HashMap<>();
        List<ReportListDto> reports = reportService.getReportByUser(memberId);

        if(reports != null) {
            response.put("result", true);
            response.put("message", "Check User List Success");
            response.put("contents", reports);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Check User List Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }

    /**
     * 모든 신고 리스트
     */
    @GetMapping("/list/all")
    public ResponseEntity<Map<String, Object>> getAllReport()
    {
        Map<String, Object> response = new HashMap<>();
        List<ReportListDto> reports = reportService.getAllReport();

        if (reports != null) {
            response.put("result", true);
            response.put("message", "Check User List Success");
            response.put("contents", reports);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Check User List Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }

    @Value("${cloud.aws.s3.bucket}")
    private String S3Bucket;// Bucket 이름

    @Autowired
    AmazonS3Client amazonS3Client;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(MultipartFile[] multipartFileList) throws Exception {
        List<String> imagePathList = new ArrayList<>();

        for(MultipartFile multipartFile: multipartFileList) {
            String originalName = multipartFile.getOriginalFilename(); // 파일 이름
            long size = multipartFile.getSize(); // 파일 크기

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(multipartFile.getContentType());
            objectMetaData.setContentLength(size);

            // S3에 업로드
            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); // 접근가능한 URL 가져오기
            imagePathList.add(imagePath);
        }

        return new ResponseEntity<Object>(imagePathList, HttpStatus.OK);
    }

    // 응답 메서드
    private ResponseEntity<Map<String, Object>> createResponse(boolean result, String successMessage, String failMessage) {
        Map<String, Object> response = new HashMap<>();
        if (result) {
            response.put("result", true);
            response.put("message", successMessage);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", failMessage);
            return ResponseEntity.badRequest().body(response);
        }
    }





}
