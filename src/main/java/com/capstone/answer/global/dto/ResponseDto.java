package com.capstone.answer.global.dto;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseDto {
    public ResponseEntity<Map<String, Object>> createResponse(boolean result, String message, Map<String, Object> response) {

        response.put("result", result);
        response.put("message", message);

        return result ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }
}
