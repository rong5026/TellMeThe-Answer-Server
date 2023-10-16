package com.capstone.answer.domain.member.service;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberSignUpAndLoginDto;
import com.capstone.answer.domain.member.dto.MemberUpdateDto;
import com.capstone.answer.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    private EntityManager em;

    private String email = "testEmail";
    private String password = "password";

    @AfterEach
    void afterEach(){
        em.clear();
        em.flush();
    }

    MemberSignUpAndLoginDto makeMemberSignUpDto(){
        MemberSignUpAndLoginDto memberSignUpAndLoginDto = new MemberSignUpAndLoginDto(email, password);
        return memberSignUpAndLoginDto;
    }

    @Test
    void 성공_회원가입() throws Exception{
        //given
        MemberSignUpAndLoginDto memberDto = makeMemberSignUpDto();

        //when
        Member member1 = memberService.signUp(memberDto);
        Member member2 = memberRepository.findByEmail(memberDto.email()).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(member1).isEqualTo(member2);
    }

    @Test
    void 성공_로그인() throws Exception{
        //given
        MemberSignUpAndLoginDto memberDto = makeMemberSignUpDto();

        //when
        memberService.signUp(memberDto);
        Member findMember = memberRepository.findByEmail(memberDto.email()).orElseThrow(() -> new Exception("존재하지 않는 회원입니다."));
        Optional<Member> loginMember = memberService.login(memberDto);

        //then
        assertThat(findMember).isEqualTo(loginMember.get());
    }

    @Test
    void 실패_로그인() throws Exception{
        //given
        MemberSignUpAndLoginDto memberSignUpDto = makeMemberSignUpDto();
        MemberSignUpAndLoginDto memberLoginDto = new MemberSignUpAndLoginDto(email, "test");

        //when
        memberService.signUp(memberSignUpDto);
        Optional<Member> loginMember = memberService.login(memberLoginDto);

        //then
        assertThat(loginMember).isEmpty();
    }

    @Test
    void 성공_비밀번호_수정() throws Exception {
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .build();
       memberRepository.save(member);


        //when
        MemberUpdateDto memberUpdateDto =
                new MemberUpdateDto(
                        Optional.of("testEmail"),
                        Optional.of("updatePassword"),
                        Optional.of((float)12.1),
                        Optional.of((float)13.5));

        memberService.update(memberUpdateDto);
        Member findMember = memberRepository.findByEmail("testEmail").orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(findMember.getPassword()).isEqualTo("updatePassword");
    }
    @Test
    void 성공_위도_수정() throws Exception{
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float)14.1)
                .latitude((float)15.3)
                .build();
        memberRepository.save(member);


        //when
        MemberUpdateDto memberUpdateDto =
                new MemberUpdateDto(
                        Optional.of("testEmail"),
                        Optional.empty(),
                        Optional.of((float)12.1),
                        Optional.empty());

        memberService.update(memberUpdateDto);
        Member findMember = memberRepository.findByEmail("testEmail").orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(findMember.getLatitude()).isEqualTo((float)12.1);
    }

    @Test
    void 성공_경도_수정() throws Exception{
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float)14.1)
                .latitude((float)15.3)
                .build();
        memberRepository.save(member);


        //when
        MemberUpdateDto memberUpdateDto =
                new MemberUpdateDto(
                        Optional.of("testEmail"),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of((float)12.1));

        memberService.update(memberUpdateDto);
        Member findMember = memberRepository.findByEmail("testEmail").orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(findMember.getLongitude()).isEqualTo((float)12.1);
    }

    @Test
    void 싪패_정보수정_회원이_존재하지_않음() throws Exception{
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float)14.1)
                .latitude((float)15.3)
                .build();
        memberRepository.save(member);

        //when
        MemberUpdateDto memberUpdateDto =
                new MemberUpdateDto(
                        Optional.of("errorEmail"),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of((float)12.1));

        //then
        assertThrows(Exception.class, ()->memberService.update(memberUpdateDto));
    }

    @Test
    void 싪패_정보수정_이메일이_NULL() throws Exception{
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float)14.1)
                .latitude((float)15.3)
                .build();
        memberRepository.save(member);

        //when
        MemberUpdateDto memberUpdateDto =
                new MemberUpdateDto(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of((float)12.1));

        //then
        assertThrows(Exception.class, ()->memberService.update(memberUpdateDto));
    }

    @Test
    void 성공_회원삭제(){
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .longitude((float)14.1)
                .latitude((float)15.3)
                .build();
        Member saveMember = memberRepository.save(member);

        //when
        memberService.delete(saveMember.getId());
        Optional<Member> findMember = memberRepository.findById(saveMember.getId());

        //then
        assertThat(findMember).isEmpty();
    }

    @Test
    void 성공_회원조회(){
        //given
        MemberSignUpAndLoginDto memberSignUpAndLoginDto1 = new MemberSignUpAndLoginDto(email, password);
        MemberSignUpAndLoginDto memberSignUpAndLoginDto2 = new MemberSignUpAndLoginDto("email2", "password");
        memberService.signUp(memberSignUpAndLoginDto1);
        memberService.signUp(memberSignUpAndLoginDto2);

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
    }
}
