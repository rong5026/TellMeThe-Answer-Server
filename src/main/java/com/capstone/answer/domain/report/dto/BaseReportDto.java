package com.capstone.answer.domain.report.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BaseReportDto {
    @Schema(type = "string" ,description = "신고내역 제목", example = "제목입니다")
    private String title;
    @Schema(type = "string" ,description = "신고내역 본문", example = "본문입니다")
    private String content;
    @Schema(type = "float" ,description = "신고지점 위도", example = "323.11")
    private float latitude;
    @Schema(type = "float" ,description = "신고지점 경도", example = "123.12")
    private float longitude;
    @Schema(type = "string" ,description = "작물명", example = "토마토")
    private String crop;
    @Schema(type = "string" ,description = "병해명", example = "토마토곰팡이병")
    private String disease;
    @Schema(type = "string" ,description = "지역", example = "서울시 광진구")
    private String location;


}
