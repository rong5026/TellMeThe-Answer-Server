package com.capstone.answer.domain.member.dto;

import com.capstone.answer.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberSignUpAndLoginDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Builder
    public Member toEntity(){
        return Member
                .builder()
                .email(email)
                .password(password)
                .build();
    }
}

