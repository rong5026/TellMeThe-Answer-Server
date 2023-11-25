package com.capstone.answer.domain.report.dto;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.report.entity.Image;
import com.capstone.answer.domain.report.entity.Report;

public record ImageDto(Report report, String imageLink) {
    public Image entity(){
        return Image
                .builder()
                .report(report)
                .imageLink(imageLink)
                .build();
    }
}

