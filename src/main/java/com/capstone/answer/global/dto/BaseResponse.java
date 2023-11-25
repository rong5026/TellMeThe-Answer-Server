package com.capstone.answer.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class BaseResponse {

    @Schema( type = "boolean", example = "true")
    private boolean result;
    @Schema( type = "string", example = "데이터 조회에 성공하였습니다.")
    private String message;

    public BaseResponse(boolean success, String message) {
        this.result = success;
        this.message = message;
    }
}
