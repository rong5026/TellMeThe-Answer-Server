package com.capstone.answer.domain.member.controller;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberSignUpDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Tag(name = "member", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    private final MemberService memberService;


    @Operation(summary = "회원가입", description = "유저정보 저장")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@RequestBody MemberSignUpDto memberSignUpDto){
        memberService.signUp(memberSignUpDto);
    }

    @Operation(summary = "로그인", description = "email, password로 로그인")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody String email, @RequestBody String password){
        memberService.login(email, password);
    }

    @Operation(summary = "회원수정", description = "회원 email, password, 위치정보 변경")
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody MemberUpdateDto memberUpdateDto) throws Exception {
        memberService.update(memberUpdateDto);
    }

    @Operation(summary = "회원삭제", description = "회원 email(id)로 삭제")
    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long memberId){
        memberService.delete(memberId);
    }


    @Operation(summary = "회원정보 조회", description = "회원 email(id)로 조회")
    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public Member getInfo(@PathVariable Long memberId) throws Exception {
        return memberService.getInfo(memberId);
    }
}
