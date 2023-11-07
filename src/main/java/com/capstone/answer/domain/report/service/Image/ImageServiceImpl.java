package com.capstone.answer.domain.report.service.Image;

import com.capstone.answer.domain.report.entity.Image;
import com.capstone.answer.domain.report.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }
}
