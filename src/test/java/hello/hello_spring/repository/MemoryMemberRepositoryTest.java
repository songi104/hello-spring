package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepositiory repository = new MemoryMemberRepositiory();

    @AfterEach
    public void afterEach() {
        repository.clearStore();

    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = "+ (result == member));
        //Assertions.assertEquals(result, null);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member res1 = repository.findByName("spring1").get();
        Member res2 = repository.findByName("spring2").get();

        Assertions.assertThat(member1).isEqualTo(res1);
        Assertions.assertThat(member2).isEqualTo(res2);
        Assertions.assertThat(res1).isNotEqualTo(res2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }



}
