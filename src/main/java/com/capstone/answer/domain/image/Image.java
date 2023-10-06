package com.capstone.answer.domain.image;

import com.capstone.answer.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "image")
@Getter
public class Image extends BaseTimeEntity {

    @Id
    private Long id;
}
