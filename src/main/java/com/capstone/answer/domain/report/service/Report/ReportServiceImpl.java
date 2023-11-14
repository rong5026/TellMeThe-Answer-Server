package com.capstone.answer.domain.report.service.Report;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.repository.MemberRepository;
import com.capstone.answer.domain.report.dto.*;
import com.capstone.answer.domain.report.entity.Image;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.repository.ImageRepository;
import com.capstone.answer.domain.report.repository.ReportRepository;
import com.capstone.answer.domain.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final S3Service s3Service;

    /**
     * 신고추가
     */
    @Override
    public void add(ReportAddDto reportAddDto) throws IOException {

        Member member = memberRepository.findReportByEmail(reportAddDto.getEmail());
        Report inputReport = Report.createReport(reportAddDto, member);

        MultipartFile[] multipartFileList = reportAddDto.getMultipartFileList();
        if (multipartFileList != null) {
            // S3 저장
            List<String> imagePathList = s3Service.saveUploadFile(multipartFileList);
            // 본문 DB저장
            Report report = reportRepository.save(inputReport);
            // 이미지 링크 DB저장
            ImageDto imageDto = new ImageDto(report, imagePathList.get(0));
            Image image = imageDto.entity();
            imageRepository.save(image);
        }
    }

    /**
     * 신고수정
     */
    @Override
    public boolean update(ReportUpdateDto reportUpdateDto) throws IOException {

        Optional<Report> optionalReport = reportRepository.findById(reportUpdateDto.getReportId());

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            updateFieldIfNotNull(report::updateTitle, reportUpdateDto.getTitle());
            updateFieldIfNotNull(report::updateContent, reportUpdateDto.getContent());
            updateFieldIfNotNull(report::updatePlant, reportUpdateDto.getPlant());
            updateFieldIfNotNull(report::updateDisease, reportUpdateDto.getDisease());
            report.updateLocation(reportUpdateDto.getLatitude(), reportUpdateDto.getLongitude());

            // S3 저장
            MultipartFile[] multipartFileList = reportUpdateDto.getMultipartFileList();
            if (multipartFileList != null) {
                List<String> imagePathList = s3Service.saveUploadFile(multipartFileList);
                String firstImagePath = imagePathList.get(0);

                // image link 업데이트
                List<Image> imageList = report.getImageLink();
                Image image = imageList.get(0);
                image.updateImageLink(firstImagePath);
            }

            reportRepository.save(report);
            return true;
        }
        return false;
    }

    /**
     * 신고삭제
     */
    @Override
    public boolean delete(Long reportId) {

        Optional<Report> optionalReport = reportRepository.findById(reportId);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            reportRepository.delete(report);
            return true;
        }
        return false;
    }

    /**
     * 유저 신고내역 조회
     */
    @Override
    public List<ReportListDto> getReportByUser(Long memberId) {
        List<Report> reports =reportRepository.getReportsByMemberId(memberId);

        List<ReportListDto> reportList = reports.stream()
                .map(this::convertToReportListAll)
                .collect(Collectors.toList());

        return reportList;
    }

    /**
     * 모든 신고리스트 조회
     */
    @Override
    public List<ReportListDto> getAllReport() {
        List<Report> reports = reportRepository.findAll();
        List<ReportListDto> reportList = reports.stream()
                .map(this::convertToReportListAll)
                .toList();

        return reportList;
    }

    // 값이 있는지 확인
    private <T> void updateFieldIfNotNull(Consumer<T> updateMethod, T value) {
        if (value != null) {
            updateMethod.accept(value);
        }
    }

    // ReportAllList 할 때 DTO로 전환
    private ReportListDto convertToReportListAll(Report report) {
        ReportListDto dto = new ReportListDto();
        dto.setReportId(report.getReportId());
        dto.setTitle(report.getTitle());
        dto.setContent(report.getContent());
        dto.setLatitude(report.getLatitude());
        dto.setLongitude(report.getLongitude());
        dto.setPlant(report.getPlant());
        dto.setDisease(report.getDisease());
        dto.setMemberId(report.getMember().getId());
        dto.setImageLink(report.getImageLink());
        return dto;
    }
}
