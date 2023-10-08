package com.capstone.answer.domain.member.repository;

import com.capstone.answer.domain.member.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void 성공_회원가입_모든정보입력() throws Exception {

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
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 성공_로그인_요청() throws Exception{
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float) 12.4)
                .latitude((float)15.6)
                .build();
        String email = "testEmail";
        String password = "testPassword";

        //when
        memberRepository.save(member);
        Member loginMember = memberRepository.findByEmailAndPassword(email, password).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(member).isEqualTo(loginMember);
    }

    @Test
    void 실패_로그인_정보_불일치() throws Exception{
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float) 12.4)
                .latitude((float)15.6)
                .build();
        String email = "testEmail";
        String password = "password";

        //when
        Optional<Member> loginMember = memberRepository.findByEmailAndPassword(email, password);

        //then
        assertThat(loginMember.isEmpty()).isTrue();
    }
    @Test
    void 성공_회원가입_위치누락() throws Exception {

        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .build();

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 실패_회원가입_비밀번호_누락() throws Exception {

        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .longitude((float) 12.4)
                .latitude((float)15.6)
                .build();

        //when, then
        assertThrows(Exception.class, ()-> memberRepository.save(member));
    }
    @Test
    void 실패_회원가입_이메일_누락() throws Exception {

        //given
        Member member = Member
                .builder()
                .password("testPassword")
                .longitude((float) 12.4)
                .latitude((float)15.6)
                .build();

        //when, then
        assertThrows(Exception.class, ()-> memberRepository.save(member));
    }

    @Test
    void 성공_위치수정(){
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float) 12.4)
                .latitude((float)15.6)
                .build();

        //when
        Member saveMember = memberRepository.save(member);

        saveMember.updateLatitude((float) 10.1);
        saveMember.updateLongitude((float) 10.1);
        saveMember = memberRepository.save(saveMember);

        //then
        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    void 성공_이메일로_회원찾기() throws Exception{
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
        Member findMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(member).isEqualTo(findMember);
    }
}