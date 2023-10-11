package com.capstone.answer.domain.member.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.util.Optional;

public record MemberUpdateDto (
        Optional<String> email,
        Optional<String> password,
        Optional<Float> latitude,
        Optional<Float> longitude
){
}
