package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest { //junit 4까지는 public이 있어야 했지만 5부터 생략해도 됨.

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 30);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member byId = memberRepository.findById(saveMember.getId());
        assertThat(byId).isEqualTo(saveMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("m1", 27);
        Member member2 = new Member("m2", 32);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> res = memberRepository.findAll();

        //then
        assertThat(res.size()).isEqualTo(2);
        assertThat(res).contains(member1, member2);

    }

}
