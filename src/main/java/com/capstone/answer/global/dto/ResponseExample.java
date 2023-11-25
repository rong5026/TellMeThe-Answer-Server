package com.capstone.answer.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseExample {

    @Schema(description = "결과")
    private boolean result;

    @Schema(description = "메시지")
    private String message;


}
