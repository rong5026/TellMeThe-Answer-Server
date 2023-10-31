package com.capstone.answer.domain.report.dto;

import com.capstone.answer.domain.report.entity.Report;
import lombok.Getter;

@Getter
public class ReportAddDto {

    private String title;
    private String content;
    private float latitude;
    private float longitude;
    private String plant;
    private String disease;
    private String email; // 사용자 이메일


}
