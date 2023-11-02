package com.capstone.answer.domain.report.service;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.repository.MemberRepository;
import com.capstone.answer.domain.report.dto.ReportAddDto;
import com.capstone.answer.domain.report.dto.ReportUpdateDto;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    /**
     * 신고추가
     */
    @Override
    public Report add(ReportAddDto reportAddDto) {

        Member member = memberRepository.findReportByEmail(reportAddDto.getEmail());
        Report inputReport = Report.createReport(reportAddDto, member);

        return reportRepository.save(inputReport);
    }

    /**
     * 신고수정
     */
    @Override
    public boolean update(ReportUpdateDto reportUpdateDto) {

        Optional<Report> optionalReport = reportRepository.findById(reportUpdateDto.getId());

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            updateFieldIfNotNull(report::updateTitle, reportUpdateDto.getTitle());
            updateFieldIfNotNull(report::updateContent, reportUpdateDto.getContent());
            report.updateLocation(reportUpdateDto.getLatitude(), reportUpdateDto.getLongitude());
            updateFieldIfNotNull(report::updatePlant, reportUpdateDto.getPlant());
            updateFieldIfNotNull(report::updateDisease, reportUpdateDto.getDisease());

            reportRepository.save(report);

            return true;
        }
        return false;
    }

    /**
     * 신고삭제
     */
    @Override
    public boolean delete(Long reportId) {

        Optional<Report> optionalReport = reportRepository.findById(reportId);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            reportRepository.delete(report);
            return true;
        }
        return false;
    }

    // 값이 있는지 확인
    private <T> void updateFieldIfNotNull(Consumer<T> updateMethod, T value) {
        if (value != null) {
            updateMethod.accept(value);
        }
    }
}
