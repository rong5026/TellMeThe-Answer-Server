package com.capstone.answer.domain.report.dto;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.report.entity.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportListDto extends BaseReportDto{
    
    private Long reportId;
    private Long memberId;
    private List<Image> imageLink;

}

