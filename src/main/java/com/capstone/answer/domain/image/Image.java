package com.capstone.answer.domain.image;

import com.capstone.answer.domain.BaseTimeEntity;
import com.capstone.answer.domain.report.entity.Report;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report reports;

    @Column(name = "image_Link")
    private String imageLink;
}
