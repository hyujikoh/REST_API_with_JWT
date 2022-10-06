package com.example.rest_api_with_jwt.app.member.controller;


import com.example.rest_api_with_jwt.app.base.dto.RsData;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginReq;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginRes;
import com.example.rest_api_with_jwt.app.member.entity.Member;
import com.example.rest_api_with_jwt.app.member.service.MemberService;
import com.example.rest_api_with_jwt.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping(value="/member/login")
    public ResponseEntity<RsData> Login(@RequestBody PostLoginReq postLoginReq){
        if(postLoginReq.isNotValid()){
            return Util.spring.responseEntityOf(RsData.of("F-1", "로그인 정보가 올바르지 않습니다."));
        }
        Member member = memberService.findByUsername(postLoginReq.getUsername()).orElse(null);

        if (member == null) {
            return Util.spring.responseEntityOf(RsData.of("F-2", "일치하는 회원이 존재하지 않습니다."));
        }
        if (passwordEncoder.matches(postLoginReq.getPassword(), member.getPassword()) == false) {
            return Util.spring.responseEntityOf(RsData.of("F-3", "비밀번호가 일치하지 않습니다."));
        }


        PostLoginRes postLoginRes = memberService.Login(postLoginReq);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", "JWT_Access_Token");
        return Util.spring.responseEntityOf(RsData.of("S-1", "로그인 성공, Access Token을 발급합니다."), headers);
    }

}
