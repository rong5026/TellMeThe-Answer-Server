package com.capstone.answer.domain.member.service;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberSignUpDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;

import java.util.Optional;

public interface MemberService {

    //회원가입
    Member signUp(MemberSignUpDto memberSignUpDto);

    //로그인
    Optional<Member> login(String email, String password);

    //회원수정
    void update(MemberUpdateDto memberUpdateDto) throws Exception;

    //회원탈퇴
    void delete(Long memberId);

    //회원 정  조회
    Member getInfo(Long MemberId) throws Exception;
}
