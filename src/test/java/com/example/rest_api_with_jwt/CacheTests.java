package com.example.rest_api_with_jwt;

import com.example.rest_api_with_jwt.app.cache.dto.Person;
import com.example.rest_api_with_jwt.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CacheTests {
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("캐시 사용")
    void t1() throws Exception {
        int rs = memberService.getCachedInt();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);

        rs = memberService.getCachedInt();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);
    }


    @Test
    @DisplayName("캐시 삭제")
    void t2() throws Exception {
        int rs = memberService.getCachedInt();

        System.out.println(rs);

        memberService.deleteCacheKey1();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);

        rs = memberService.getCachedInt();
        System.out.println(rs);
    }

    @Test
    @DisplayName("캐시 수정")
    void t3() throws Exception {
        int rs = memberService.getCachedInt();

        System.out.println(rs);

        memberService.deleteCacheKey1();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);

        rs = memberService.getCachedInt();
        System.out.println(rs);

        rs = memberService.getCachedInt();
        System.out.println(rs);

        rs = memberService.putCachedInt();
        System.out.println(rs);


        rs = memberService.getCachedInt();
        System.out.println(rs);
    }


    @Test
    @DisplayName("매개변수를 가진 캐싱")
    void t4() throws Exception {
        int re = memberService.cachePlus(3,6);// 9 // 실행(캐시생성)
        System.out.println(re);
        re= memberService.cachePlus(3,6);// 9 // 캐시사용
        System.out.println(re);
        re= memberService.cachePlus(7,2);// 7 // 실행(캐시생성)
        System.out.println(re);
        re=  memberService.cachePlus(7,2);// 7 // 캐시사용

        System.out.println(re);
    }



    @Test
    @DisplayName("래퍼런스 매개변수")
    void t5() throws Exception {
        Person p1 = new Person(1, "홍길동1");
        Person p2 = new Person(1, "홍길동2");

        System.out.println(p1.equals(p2));

        String personName = memberService.getName(p1, 5);
        System.out.println(personName);

        personName = memberService.getName(p2, 10);
        System.out.println(personName);
    }
}