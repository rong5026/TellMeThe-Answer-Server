package com.capstone.answer.domain.member.dto;

import com.capstone.answer.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberInfoDto {
    private String email;
    private String password;

    @Builder
    public MemberInfoDto(Member member){
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
