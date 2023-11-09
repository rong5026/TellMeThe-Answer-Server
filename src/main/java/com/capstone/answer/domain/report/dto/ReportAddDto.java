package com.capstone.answer.domain.report.dto;

import com.capstone.answer.domain.report.entity.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReportAddDto extends BaseReportDto{

    private String email; // 사용자 이메일

}
