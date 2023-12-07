package com.capstone.answer.domain.report.entity;


import com.capstone.answer.domain.BaseTimeEntity;
import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.report.dto.ReportAddDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "report")
@Getter
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @Column(nullable = false, length = 40)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length = 20)
    private float latitude;

    @Column(length = 20)
    private float longitude;

    @Column(nullable = false, length = 40)
    private String crop;

    @Column(nullable = false, length = 40)
    private String disease;

    @Column(length = 40)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonManagedReference
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> imageLink = new ArrayList<>();

    // 신고 생성
    public static Report createReport(ReportAddDto reportAddDto, Member member) {

        return Report.builder()
                .title(reportAddDto.getTitle())
                .content(reportAddDto.getContent())
                .latitude(reportAddDto.getLatitude())
                .longitude(reportAddDto.getLongitude())
                .crop(reportAddDto.getCrop())
                .disease(reportAddDto.getDisease())
                .location(reportAddDto.getLocation())
                .member(member)
                .build();
    }

    // 제목 업데이트
    public void updateTitle(String title){
        this.title = title;
    }

    // 본문 업데이트
    public void updateContent(String content){
        this.content = content;
    }

    // 위치 업데이트
    public void updateLocation(float latitude, float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // 식물이름 업데이트
    public void updateCrop(String crop){
        this.crop = crop;
    }

    // 병해 업데이트
    public void updateDisease(String disease){
        this.disease = disease;
    }

    // 이미지 업데이트
    public void updateImageLink(List<Image> imageLink) {
        this.imageLink = imageLink;
    }
}
