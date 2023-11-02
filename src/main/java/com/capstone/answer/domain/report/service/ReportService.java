package com.capstone.answer.domain.report.service;

import com.capstone.answer.domain.report.dto.ReportAddDto;
import com.capstone.answer.domain.report.dto.ReportUpdateDto;
import com.capstone.answer.domain.report.entity.Report;

import java.util.List;

public interface ReportService {

    // 신고 추가
    Report add(ReportAddDto reportAddDto);

    // 신고 수정
    boolean update(ReportUpdateDto reportUpdateDto);

    // 신고 삭제
    boolean delete(Long reportId);

    // 유저에 대한 신고내역 조회
    List<ReportUpdateDto> getReportByUser(Long memberId);
    // 모든 신고 내역 조회

    // 마커 신고 내역 조회

    // 신고아이디로 이미지 조회
}
