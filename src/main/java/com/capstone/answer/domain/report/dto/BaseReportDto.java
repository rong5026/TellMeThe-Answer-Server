package com.capstone.answer.domain.report.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BaseReportDto {
    private String title;
    private String content;
    private float latitude;
    private float longitude;
    private String crop;
    private String disease;

}