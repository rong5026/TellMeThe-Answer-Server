package com.capstone.answer.domain.report.service.Report;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.repository.MemberRepository;
import com.capstone.answer.domain.report.dto.*;
import com.capstone.answer.domain.report.entity.Image;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.domain.report.repository.ImageRepository;
import com.capstone.answer.domain.report.repository.ReportRepository;
import com.capstone.answer.domain.report.utils.Utils;
import com.capstone.answer.domain.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

        Member member = memberRepository.findMemberById(reportAddDto.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        Report inputReport = Report.createReport(reportAddDto, member);

        MultipartFile[] multipartFileList = reportAddDto.getMultipartFileList();
        if (multipartFileList != null) {
            // S3 저장
            List<String> imagePathList = s3Service.saveUploadFile(multipartFileList);
            // 본문 DB저장
            Report report = reportRepository.save(inputReport);
            // 이미지 링크 DB저장
            ImageDto imageDto = new ImageDto(report, imagePathList.get(0)); // 첫번째 사진만 저장
            Image image = imageDto.entity();
            imageRepository.save(image);
        }
    }

    /**
     * 신고수정
     */
    @Override
    public void update(ReportUpdateDto reportUpdateDto) throws IOException {

        Report report = reportRepository.findById(reportUpdateDto.getReportId())
                .orElseThrow(()-> new NoSuchElementException("존재하지 않은 신고내역입니다."));

        Optional.ofNullable(reportUpdateDto.getTitle()).ifPresent(report::updateTitle);
        Optional.ofNullable(reportUpdateDto.getContent()).ifPresent(report::updateContent);
        Optional.ofNullable(reportUpdateDto.getCrop()).ifPresent(report::updateCrop);
        Optional.ofNullable(reportUpdateDto.getDisease()).ifPresent(report::updateDisease);
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
    }

    /**
     * 신고삭제
     */
    @Override
    public void delete(Long reportId) {

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 신고내역입니다."));

        reportRepository.delete(report);
    }

    /**
     * 유저 신고내역 조회
     */
    @Override
    public List<ReportListDto> getReportByUser(Long memberId) {
        List<Report> reports =reportRepository.getReportsByMemberId(memberId) ;

        if (reports.isEmpty()) {
            throw new NoSuchElementException("유저에 대한 신고내역이 없습니다.");
        }
        List<ReportListDto> reportList = reports.stream()
                .map(Utils::convertToReportListAll)
                .collect(Collectors.toList());

        return reportList;
    }

    /**
     * 모든 신고리스트 조회
     */
    @Override
    public List<ReportListDto> getAllReport() {
        List<Report> reports = reportRepository.findAll();

        if (reports.isEmpty()) {
            throw new NoSuchElementException("신고내역이 없습니다.");
        }

        return reports.stream()
                .map(Utils::convertToReportListAll)
                .toList();
    }

}
