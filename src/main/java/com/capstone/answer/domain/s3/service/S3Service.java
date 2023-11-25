package com.capstone.answer.domain.s3.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface S3Service {

    // S3 이미지 저장
    List<String> saveUploadFile(MultipartFile[] multipartFileList) throws IOException;

    // S3 이미지 수정

    // S3 이미지 삭제

}
