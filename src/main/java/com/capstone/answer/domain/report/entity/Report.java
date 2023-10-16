package com.capstone.answer.domain.report.entity;


import com.capstone.answer.domain.BaseTimeEntity;
import com.capstone.answer.domain.image.Image;
import com.capstone.answer.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "report")
@Getter
public class Report extends BaseTimeEntity {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(length = 20)
    private float latitude;

    @Column(length = 20)
    private float longitude;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 40)
    private String plant;

    @Column(nullable = false, length = 40)
    private String disease;

    @OneToMany(mappedBy = "reports", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Image> imageLink = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;


    // == 게시글 업데이트 == //
    // 제목 업데이트
    public void updateTitle(String title){
        this.title = title;
    }
    // 위치 업데이트
    public void updateLocation(String location){
        this.location = location;
    }
    // 본문 업데이트
    public void updateContent(String content){
        this.content = content;
    }
    // 식물이름 업데이트
    public void updatePlant(String plant){
        this.plant = plant;
    }
    // 병해 업데이트
    public void updateDisease(String disease){
        this.disease = disease;
    }

}
