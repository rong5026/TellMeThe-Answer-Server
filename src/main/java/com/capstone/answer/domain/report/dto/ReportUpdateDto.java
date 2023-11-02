package com.capstone.answer.domain.report.dto;

import lombok.Getter;

@Getter
public class ReportUpdateDto {

    private Long id;
    private String title;
    private String content;
    private float latitude;
    private float longitude;
    private String plant;
    private String disease;
}
