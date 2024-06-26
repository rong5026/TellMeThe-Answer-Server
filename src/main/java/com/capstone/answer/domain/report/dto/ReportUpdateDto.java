package com.capstone.answer.domain.report.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReportUpdateDto extends BaseReportDto{

    private Long reportId;
    private MultipartFile[] multipartFileList; // 업로드 이미지

}

