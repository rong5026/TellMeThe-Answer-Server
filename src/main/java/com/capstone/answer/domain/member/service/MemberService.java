package com.capstone.answer.domain.member.service;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberInfoDto;
import com.capstone.answer.domain.member.dto.MemberSignUpAndLoginDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;

import java.util.Optional;

public interface MemberService {

    //회원가입
    Member signUp(MemberSignUpAndLoginDto memberSignUpAndLoginDto);

    Long login(MemberSignUpAndLoginDto memberSignUpAndLoginDto);

    //회원수정
    void update(MemberUpdateDto memberUpdateDto) throws Exception;

    //회원탈퇴
    void delete(Long memberId);

    //회원 정  조회
    MemberInfoDto getInfo(Long MemberId) throws Exception;
}
