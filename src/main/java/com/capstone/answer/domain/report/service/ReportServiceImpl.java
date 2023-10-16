package com.capstone.answer.domain.report.service;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.repository.MemberRepository;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    /**
     * 신고추가
     */
    @Override
    public Report addReport(Report report, String memberEmail) {

        Member member = memberRepository.findReportByEmail(memberEmail);

        Report inputReport = Report.createReport(report, member);

        return reportRepository.save(inputReport);
    }
}
