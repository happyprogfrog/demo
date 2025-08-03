package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

//    @AfterEach
//    public void afterEach() {
//        repository.clearStore();
//    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("kero");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("kero1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kero2");
        repository.save(member2);

        // when
        Member result = repository.findByName("kero1").get();

        // then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("kero1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kero2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}