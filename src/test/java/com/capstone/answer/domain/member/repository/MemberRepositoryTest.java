package com.capstone.answer.domain.member.repository;

import com.capstone.answer.domain.member.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void afterEach(){
        em.clear();
        em.flush();
    }

    @Test
    void 성공_회원가입_모든정보입력(){

        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float) 12.4)
                .latitude((float)15.6)
                .build();

        //when
        memberRepository.save(member);

        //then
        Assertions.assertThat(member).isEqualTo(1);
    }
}