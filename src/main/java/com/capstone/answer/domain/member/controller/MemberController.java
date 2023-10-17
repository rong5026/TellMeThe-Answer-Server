package com.capstone.answer.domain.member.controller;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberInfoDto;
import com.capstone.answer.domain.member.dto.MemberSignUpAndLoginDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/member/signup")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@RequestBody MemberSignUpAndLoginDto memberSignUpAndLoginDto){
        memberService.signUp(memberSignUpAndLoginDto);
    }

    /**
     * 로그인
     */
    @PostMapping("/member/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody MemberSignUpAndLoginDto memberSignUpAndLoginDto){
        memberService.login(memberSignUpAndLoginDto);
    }

    /**
     * 회원수정
     */
    @PostMapping("/member/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody MemberUpdateDto memberUpdateDto) throws Exception {
        memberService.update(memberUpdateDto);
    }

    /**
     * 회원삭제
     */
    @DeleteMapping("/member/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long memberId){
        memberService.delete(memberId);
    }

    /**
     * 정보조회
     */
    @GetMapping("/member/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberInfoDto getInfo(@PathVariable Long memberId) throws Exception {
        return memberService.getInfo(memberId);
    }
}
