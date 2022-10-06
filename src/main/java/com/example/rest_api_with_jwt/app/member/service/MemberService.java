package com.example.rest_api_with_jwt.app.member.service;

import com.example.rest_api_with_jwt.app.member.dto.PostLoginReq;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginRes;
import com.example.rest_api_with_jwt.app.member.entity.Member;
import com.example.rest_api_with_jwt.app.member.repository.MemberRepository;
import com.example.rest_api_with_jwt.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

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

    public PostLoginRes Login(PostLoginReq postLoginReq) {

        PostLoginRes postLoginRes = new PostLoginRes();
        postLoginRes.setUsername(postLoginReq.getUsername());

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", postLoginReq.getUsername());
        claims.put("password", postLoginReq.getPassword());
        claims.put("authorities", Arrays.asList(

                new SimpleGrantedAuthority("MEMBER"))
        );
        postLoginRes.setAccessToken(jwtProvider.generateAccessToken(claims, 60 * 60 * 5));
        return postLoginRes;

    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}