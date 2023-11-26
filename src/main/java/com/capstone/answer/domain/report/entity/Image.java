package com.capstone.answer.domain.report.entity;

import com.capstone.answer.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "image")
@Getter
public class Image extends BaseTimeEntity {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "image_id")
    @Schema(type = "Long" ,description = "이미지 고유 ID", example = "3")
    private Long imageId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    @Schema(hidden = true)
    private Report report;

    @Column(name = "image_Link")
    @Schema(type = "string" ,description = "이미지 URL", example = "https://search.pstatic.net/common/?src=wdfw.png")
    private String imageLink;

    public void updateImageLink(String imageLink){
        this.imageLink = imageLink;
    }
}

