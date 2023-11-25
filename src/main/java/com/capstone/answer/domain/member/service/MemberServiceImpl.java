package com.capstone.answer.domain.member.service;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberInfoDto;
import com.capstone.answer.domain.member.dto.MemberSignUpAndLoginDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.repository.MemberRepository;
import com.capstone.answer.domain.report.entity.Report;
import com.capstone.answer.global.utils.Constants;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.sound.midi.MetaMessage;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Override
    public void signUp(MemberSignUpAndLoginDto requestDto) throws Exception {

        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw  new Exception("이미 존재하는 이메일입니다.");
        }
        memberRepository.save(requestDto.toEntity());
    }

    /**
     * 로그인
     */
    public Long login(MemberSignUpAndLoginDto requestDto) {

        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                ()-> new IllegalArgumentException("가입되지 않은 email입니다.")
        );

        String password = requestDto.getPassword();
        if (!(Objects.equals(member.getPassword(), password))) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        return member.getId();
    }

    /**
     * 정보 업데이트
     */
    @Override
    public void update(MemberUpdateDto memberUpdateDto) throws Exception{
        memberUpdateDto.email().orElseThrow(()-> new Exception("이메일이 없습니다.")); // 인자에 이메일 없음
        Member member = memberRepository.findByEmail(memberUpdateDto.email().get()).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));
        memberUpdateDto.password().ifPresent(password -> {
            if (!password.trim().isEmpty()) {
                member.updatePassword(password);
            }
        });
        memberUpdateDto.latitude().ifPresent(member::updateLatitude);
        memberUpdateDto.longitude().ifPresent(member::updateLongitude);
        memberRepository.save(member);
    }

    /**
     * 회원삭제
     */
    @Override
    public void delete(Long memberId) {
        memberRepository.findById(memberId).ifPresentOrElse(
                deleteMember -> memberRepository.delete(deleteMember),
                () -> new Exception("존재하지 않는 회원입니다.")
        );
    }

    /**
     * 회원 정보 조회
     */
    @Override
    public MemberInfoDto getInfo(Long memberId) throws Exception{
        Member findMember = memberRepository.findById(memberId).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));
        MemberInfoDto memberInfoDto = new MemberInfoDto(findMember);
        return memberInfoDto;
    }
}
