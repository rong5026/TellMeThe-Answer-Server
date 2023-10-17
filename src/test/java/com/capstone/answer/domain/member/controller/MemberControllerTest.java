package com.capstone.answer.domain.member.controller;

import com.capstone.answer.domain.member.Member;
import com.capstone.answer.domain.member.dto.MemberInfoDto;
import com.capstone.answer.domain.member.dto.MemberSignUpAndLoginDto;
import com.capstone.answer.domain.member.repository.MemberRepository;
import com.capstone.answer.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class MemberControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private EntityManager em;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void afterEach(){
        em.clear();
        em.flush();
    }
    MemberSignUpAndLoginDto makeMemberSignUpDto(){
        MemberSignUpAndLoginDto memberSignUpAndLoginDto = new MemberSignUpAndLoginDto("testEmail", "TestPassword");
        return memberSignUpAndLoginDto;
    }


    @Test
    void 성공_회원가입() throws Exception{
        //given
        Map<String, String> map = new HashMap<>();
        map.put("email", "testEmail");
        map.put("password", "testPassword");

        String memberSignUpDto = objectMapper.writeValueAsString(map);

        //when
        mockMvc.perform(post("/member/signup")
                        .content(memberSignUpDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Member member = memberRepository.findByEmail("testEmail").orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));
        //MemberSignUpDto memberResult = objectMapper.readValue(result.getResponse().getContentAsString(), MemberSignUpDto.class);

        //then
        assertThat(member.getEmail()).isEqualTo("testEmail");
    }

    @Test
    void 성공_로그인() throws Exception {
        //given
        MemberSignUpAndLoginDto memberSignUpDto = makeMemberSignUpDto();
        memberService.signUp(memberSignUpDto);

        //when, then
        Map<String, String> map = new HashMap<>();
        map.put("email", "testEmail");
        map.put("password", "testPassword");
        String memberLoginDto = objectMapper.writeValueAsString(map);

        mockMvc.perform(post("/member/login")
                .content(String.valueOf(memberLoginDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 성공_회원수정_비밀번호() throws Exception {
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .build();
        memberRepository.save(member);

        Map<String, String> map = new HashMap<>();
        map.put("email", "testEmail");
        map.put("password", "updatePassword");
        String memberUpdateDto = objectMapper.writeValueAsString(map);

        //when
        mockMvc.perform((post("/member/update")
                        .content(memberUpdateDto)
                        .contentType(MediaType.APPLICATION_JSON)))
                        .andExpect(status().isOk());
        Member findMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(()-> new Exception("존재하지 않는 회원입니다."));

        //then
        assertThat(findMember.getPassword()).isEqualTo("updatePassword");
    }

    @Test
    void 성공_회원삭제() throws Exception {
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .build();
        Member saveMember = memberRepository.save(member);
        Long id = saveMember.getId();

        //when
        mockMvc.perform(delete("/member/{id}", id))
                .andExpect(status().isOk());

        //then
        Optional<Member> findMember = memberRepository.findById(id);
        assertThat(findMember).isEmpty();
    }
    
    @Test
    void 성공_회원조회() throws Exception{
        //given
        Member member = Member
                .builder()
                .email("testEmail")
                .password("testPassword")
                .build();
        Member saveMember = memberRepository.save(member);
        Long id = saveMember.getId();

        //when
        String response = mockMvc.perform(get("/member/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        MemberInfoDto findMember = objectMapper.readValue(response, MemberInfoDto.class);

        //then
        assertThat(findMember.getEmail()).isEqualTo(saveMember.getEmail());
    }
}
