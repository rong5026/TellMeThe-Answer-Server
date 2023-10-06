package com.capstone.answer.domain.member.repository;

import com.capstone.answer.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
