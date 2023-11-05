package com.capstone.answer.domain.report.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReportListDto {
    private Long id;
    private String title;
    private String content;
    private float latitude;
    private float longitude;
    private String plant;
    private String disease;
    private Long memberId;
//   이미지 리스트
}

