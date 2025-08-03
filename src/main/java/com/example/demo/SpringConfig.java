package com.example.demo;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링이 뜰 때, "이 @Configuration을 읽고 이건 스프링 빈에 등록하라는 뜻이네!"라고 인식
public class SpringConfig {

    @Bean // "스프링 빈으로 등록할거야!"라는 뜻
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
