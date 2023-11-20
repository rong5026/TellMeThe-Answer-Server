package com.capstone.answer.global.dto;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseDto {
    public ResponseEntity<Map<String, Object>> createResponse(boolean result, String successMessage, String failMessage, Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();

        response.put("data", data);

        if (result) {
            response.put("result", true);
            response.put("message", successMessage);
        } else {
            response.put("result", false);
            response.put("message", failMessage);
        }
        return result ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }
}
