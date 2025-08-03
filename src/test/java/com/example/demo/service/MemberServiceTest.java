package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원가입")
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kero");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    @DisplayName("중복 회원 예외")
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kero");

        Member member2 = new Member();
        member2.setName("kero");

        // when
        memberService.join(member1);
        IllegalStateException ex = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2)); // 중복 회원 예외 발생

        // then
        assertThat(ex.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}