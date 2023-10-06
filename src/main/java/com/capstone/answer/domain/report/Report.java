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
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, length = 40)
    private String location;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 40)
    private String plane;

    @Column(nullable = false, length = 40)
    private String disease;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Image> imageLink = new ArrayList<>();
}
