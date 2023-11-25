package com.capstone.answer.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class BaseResponse {

    @Schema( type = "boolean", example = "true")
    private boolean result;
    @Schema( type = "string", example = "Clark Kent")
    private String message;

    public BaseResponse(boolean success, String message) {
        this.result = success;
        this.message = message;
    }
}
