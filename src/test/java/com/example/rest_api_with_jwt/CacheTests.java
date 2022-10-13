package com.example.rest_api_with_jwt;

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
    }
}