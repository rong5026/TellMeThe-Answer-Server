package com.capstone.answer.domain.report.repository;

import com.capstone.answer.domain.report.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
