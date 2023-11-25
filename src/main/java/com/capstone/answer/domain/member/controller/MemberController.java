package com.capstone.answer.domain.member.controller;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberInfoDto;
import com.capstone.answer.domain.member.dto.MemberSignUpAndLoginDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.service.MemberService;
import com.capstone.answer.global.utils.Constants;
import com.capstone.answer.global.utils.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Tag(name = "member", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    private final MemberService memberService;
    private final ResponseDto reponseDto;

    @Operation(summary = "회원가입", description = "유저정보 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = MemberSignUpAndLoginDto.class))),
            @ApiResponse(responseCode = "400", description = "회원가입 실패"),
    })
    @PostMapping("/signup")
    public void signUp(@RequestBody MemberSignUpAndLoginDto memberSignUpAndLoginDto){
        memberService.signUp(memberSignUpAndLoginDto);
    }

    @Operation(summary = "로그인", description = "email, password로 로그인")
    @PostMapping("/login")
    public Long login(@RequestBody MemberSignUpAndLoginDto memberSignUpAndLoginDto) {
        Long memberId = memberService.login(memberSignUpAndLoginDto);
        if (Objects.equals(memberId, Constants.NOT_LOGINED))
            return Constants.NOT_LOGINED;
        return memberId;
    }

    @Operation(summary = "회원수정", description = "회원 email, password, 위치정보 변경")
    @PostMapping("/update")
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
    public MemberInfoDto getInfo(@PathVariable Long memberId) throws Exception {
        return memberService.getInfo(memberId);
    }
}