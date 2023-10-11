package com.capstone.answer.domain.report.repository;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
}


