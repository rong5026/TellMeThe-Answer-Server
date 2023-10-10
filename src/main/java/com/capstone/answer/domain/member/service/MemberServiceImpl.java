package com.capstone.answer.domain.member.service;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberSignUpDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Member signUp(MemberSignUpDto memberSignUpDto) {
        Member saveMember = memberSignUpDto.entity();
        memberRepository.findByEmail(saveMember.getEmail()).ifPresent(
                value -> new Exception("이미 존재하는 이메일입니다.")
        );
        return memberRepository.save(saveMember);
    }

    /**
     * 로그인
     */
    @Override
    public Optional<Member> login(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
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
     * 회원 정보 조보
     */
    @Override
    public void getInfo(Long memberId) throws Exception{
        Member findMember = memberRepository.findById(memberId).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));
    }
}
