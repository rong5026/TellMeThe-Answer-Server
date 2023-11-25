package com.capstone.answer.domain.member.dto.Response;

import com.capstone.answer.global.dto.BaseResponse;

public class LoginResponse extends BaseResponse {

    private Long memberId;

    public LoginResponse(boolean success, String message, Long memberId) {
        super(success, message);
        this.memberId = memberId;
    }
}
