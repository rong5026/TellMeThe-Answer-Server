package com.capstone.answer.domain.report.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReportListDto extends BaseReportDto{
    
    private Long reportId;
    private Long memberId;

}

