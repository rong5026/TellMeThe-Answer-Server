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
    public Member signUp(MemberSignUpAndLoginDto memberSignUpAndLoginDto) {
        Member saveMember = memberSignUpAndLoginDto.entity();
        memberRepository.findByEmail(saveMember.getEmail()).ifPresent(
                value -> new Exception("이미 존재하는 이메일입니다.")
        );
        return memberRepository.save(saveMember);
    }

    public Long login(MemberSignUpAndLoginDto memberSignUpAndLoginDto) {
        Optional<Member> member = memberRepository.findByEmailAndPassword(memberSignUpAndLoginDto.email(), memberSignUpAndLoginDto.password());

        if (member.isPresent())
            return member.get().getId();
        return Constants.NOT_LOGINED;
    }

    /**
     * 정보 업데이트
     */
    @Override
    public void update(MemberUpdateDto memberUpdateDto) throws Exception{
        memberUpdateDto.email().orElseThrow(()-> new Exception("이메일이 없습니다.")); // 인자에 이메일 없음
        Member member = memberRepository.findByEmail(memberUpdateDto.email().get()).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));
        memberUpdateDto.password().ifPresent(member::updatePassword);
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
