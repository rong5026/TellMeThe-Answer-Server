package com.capstone.answer.domain.report.dto;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.report.entity.Image;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportListDto extends BaseReportDto{
    @Schema(type = "Long" ,description = "신고내역 고유 ID", example = "2")
    private Long reportId;
    @Schema(type = "Long" ,description = "신고한 유저 고유 ID", example = "24")
    private Long memberId;
    @Schema(type = "List<Image>" ,description = "Image객체에 대한 리스트")
    private List<Image> imageLink;
}

