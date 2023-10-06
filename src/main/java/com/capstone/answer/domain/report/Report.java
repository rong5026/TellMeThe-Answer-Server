package com.capstone.answer.domain.report;


import com.capstone.answer.domain.BaseTimeEntity;
import com.capstone.answer.domain.image.Image;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "member")
@Getter
public class Report extends BaseTimeEntity {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, length = 40)
    private String location;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 40)
    private String plant;

    @Column(nullable = false, length = 40)
    private String disease;

    @OneToMany(mappedBy = "reports", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Image> imageLink = new ArrayList<>();

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
