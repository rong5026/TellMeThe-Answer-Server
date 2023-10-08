package com.capstone.answer.domain.member;


import com.capstone.answer.domain.BaseTimeEntity;
import com.capstone.answer.domain.image.Image;
import com.capstone.answer.domain.report.Report;
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
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email", nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 40)
    private String password;

    @Column(length = 20)
    private float latitude;

    @Column(length = 20)
    private float longitude;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Report> reports = new ArrayList<>();

    // == 위도, 경도 업데이트 == //
    public void updateLatitude(float latitude){
        this.latitude = latitude;
    }

    public void updateLongitude(float longitude){
        this.longitude = longitude;
    }
}
