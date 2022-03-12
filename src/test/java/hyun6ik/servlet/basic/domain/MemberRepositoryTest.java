package hyun6ik.servlet.basic.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void clear() {
        memberRepository.deleteAll();
    }

    @Test
    @Transactional
    void save() throws ClassNotFoundException {
        //given
        final Member member = Member.of("memberA", 20);
        final Member savedMember = memberRepository.save(member);
        //when
        final Member findMember = memberRepository.findById(savedMember.getId()).orElseThrow(() -> new ClassNotFoundException("클래스없어요"));
        //then
        System.out.println("savedMember.getId() = " + savedMember.getId());
        System.out.println("findMember.getId() = " + findMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    @Transactional
    void findAll() {
        //given
        final Member member1 = Member.of("member1", 20);
        final Member member2 = Member.of("member2", 30);
        memberRepository.saveAll(List.of(member1,member2));
        //when
        final List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);

    }


}