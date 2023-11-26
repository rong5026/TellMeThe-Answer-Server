package com.capstone.answer.domain.report.dto.Response;

import com.capstone.answer.domain.report.dto.ReportListDto;
import com.capstone.answer.global.dto.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class ReportListResponse extends BaseResponse {

    private List<ReportListDto> reportListDtoList;

    public ReportListResponse(boolean success, String message, List<ReportListDto> reportListDtoList) {
        super(success, message);
        this.reportListDtoList = reportListDtoList;
    }
}
