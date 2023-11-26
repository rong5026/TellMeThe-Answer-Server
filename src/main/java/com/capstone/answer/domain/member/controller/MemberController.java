package com.capstone.answer.domain.member.controller;

import com.capstone.answer.domain.member.dto.MemberInfoDto;
import com.capstone.answer.domain.member.dto.MemberSignUpAndLoginDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.dto.Response.LoginResponse;
import com.capstone.answer.domain.member.service.MemberService;
import com.capstone.answer.global.dto.BaseResponse;
import com.capstone.answer.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "member", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "유저정보 저장" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "회원가입 실패", content = @Content),
    })
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signUp(@RequestBody MemberSignUpAndLoginDto request) throws Exception {
        memberService.signUp(request);
        return ResponseEntity.ok(new BaseResponse(true," 회원가입 성공"));
    }

    @Operation(summary = "로그인", description = "email, password로 로그인 후 memberId 리턴")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "500", description = "로그인 실패"),
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody MemberSignUpAndLoginDto request) {
        Long memberId = memberService.login(request);
        LoginResponse response = new LoginResponse(true,"로그인 성공", memberId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원수정", description = "회원 email, password, 위치정보 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원수정 성공"),
            @ApiResponse(responseCode = "500", description = "회원수정 실패"),
    })
    @PostMapping("/update")
    public void update(@RequestBody MemberUpdateDto memberUpdateDto) throws Exception {
        memberService.update(memberUpdateDto);
    }

    @Operation(summary = "회원삭제", description = "회원 email(id)로 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원삭제 성공"),
            @ApiResponse(responseCode = "500", description = "회원삭제 실패"),
    })
    @DeleteMapping("/{memberId}")
    public void delete(@PathVariable Long memberId){
        memberService.delete(memberId);
    }


    @Operation(summary = "회원정보 조회", description = "회원 email(id)로 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원조회 성공", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "500", description = "회원조회 실패", content = @Content),
    })
    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberInfoDto getInfo(@PathVariable Long memberId) throws Exception {
        return memberService.getInfo(memberId);
    }
}