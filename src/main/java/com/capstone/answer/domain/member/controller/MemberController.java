package com.capstone.answer.domain.member.controller;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberSignUpDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.service.MemberService;
import com.capstone.answer.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Tag(name = "member", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    private final MemberService memberService;
    private final ResponseDto reponseDto;

    @Operation(summary = "회원가입", description = "유저정보 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MemberSignUpDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public Long signUp(@RequestBody MemberSignUpDto memberSignUpDto){
        Map<String, Object> response = new HashMap<>();

        Long memberId = memberService.signUp(memberSignUpDto);
//
//        if (memberId != null)
//            return (reponseDto.createResponse())
//        return memberId;
        return memberId;
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
