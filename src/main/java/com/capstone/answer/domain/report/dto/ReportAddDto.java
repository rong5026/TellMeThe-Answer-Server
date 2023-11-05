package com.capstone.answer.domain.report.dto;

import com.capstone.answer.domain.report.entity.Report;
import lombok.Getter;

@Getter
public class ReportAddDto extends BaseReportDto{

    private String email; // 사용자 이메일

}
