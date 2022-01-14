package hello.servlet.domain.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member findMember = memberRepository.save(member);

        //then
        Member byMember = memberRepository.findByMember(findMember.getId());

        assertThat(byMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        Member smember1 = memberRepository.save(member1);
        Member smember2 = memberRepository.save(member2);

        //when
        List<Member> findMembers = memberRepository.findAll();

        //then
        assertThat(findMembers.size()).isEqualTo(2);
        assertThat(findMembers).contains(member1, member2);

    }
}