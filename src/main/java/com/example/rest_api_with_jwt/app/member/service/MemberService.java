package com.example.rest_api_with_jwt.app.member.service;

import com.example.rest_api_with_jwt.app.member.dto.PostLoginReq;
import com.example.rest_api_with_jwt.app.member.entity.Member;
import com.example.rest_api_with_jwt.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(String username, String password, String email) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    public void Login(PostLoginReq postLoginReq) {
        System.out.println("leladlsgasdgsdfg");

    }
}