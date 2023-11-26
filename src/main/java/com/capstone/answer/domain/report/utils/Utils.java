package com.capstone.answer.domain.report.utils;

import com.capstone.answer.domain.report.dto.ReportListDto;
import com.capstone.answer.domain.report.entity.Report;

import java.util.function.Consumer;

public class Utils {

    // ReportAllList 할 때 DTO로 전환
    public static ReportListDto convertToReportListAll(Report report) {
        ReportListDto dto = new ReportListDto();
        dto.setReportId(report.getReportId());
        dto.setTitle(report.getTitle());
        dto.setContent(report.getContent());
        dto.setLatitude(report.getLatitude());
        dto.setLongitude(report.getLongitude());
        dto.setCrop(report.getCrop());
        dto.setDisease(report.getDisease());
        dto.setMemberId(report.getMember().getId());
        dto.setImageLink(report.getImageLink());
        return dto;
    }

    // 값이 있는지 확인
    public static <T> void updateFieldIfNotNull(Consumer<T> updateMethod, T value) {
        if (value != null) {
            updateMethod.accept(value);
        }
    }

}
