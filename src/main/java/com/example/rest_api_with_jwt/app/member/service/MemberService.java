package com.example.rest_api_with_jwt.app.member.service;

import com.example.rest_api_with_jwt.app.cache.dto.Person;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginReq;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginRes;
import com.example.rest_api_with_jwt.app.member.entity.Member;
import com.example.rest_api_with_jwt.app.member.repository.MemberRepository;
import com.example.rest_api_with_jwt.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public Member join(String username, String password, String email) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public String genAccessToken(Member member) {
        String accessToken = member.getAccessToken();

        if (StringUtils.hasLength(accessToken) == false) {
            accessToken = jwtProvider.generateAccessToken(member.getAccessTokenClaims(), 60L * 60 * 24 * 365 * 100);
            member.setAccessToken(accessToken);
        }

        return accessToken;
    }

    public boolean verifyWithWhiteList(Member member, String token) {
        return member.getAccessToken().equals(token);
    }


    @Cacheable("key1")
    public int getCachedInt() {
        System.out.println("getCachedInt ?????????");
        return 5;
    }


    @CacheEvict("key1") // ??????
    public void deleteCacheKey1() {
    }

    @CachePut("key1")
    public int putCachedInt() {
        System.out.println("key1 ??????");
        return 10;
    }

    @Cacheable("plus")
    public int cachePlus(int i, int i1) {
        System.out.println("a+ b " + i+i1);
        return i+i1;
    }


    @Cacheable(value = "getName", key = "#p.id")
    public String getName(Person p, int random) {
        System.out.println("== getName ????????? ==");
        return p.getName();
    }


    @Cacheable("member")// ??????????????? ?????? ????????? ????????? ??????
    public Member getByUsername__cached(String username) {
        return findByUsername(username).orElse(null);
    }
}