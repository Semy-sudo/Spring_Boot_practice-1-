package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach()
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);//DI
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore(); //테스트 하나가 끝날때 마다 저장소 지움 -> 순서 상관 없어지게 됨
    }


    @Test
    void join() {

        //given
        Member member = new Member();
        member.setName("semy1");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {

        //given
        Member member1 = new Member();
        member1.setName("semy1");

        Member member2 = new Member();
        member2.setName("semy1");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        //then


    }

    @Test
    void findOne() {
    }
}