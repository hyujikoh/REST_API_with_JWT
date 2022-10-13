package com.example.rest_api_with_jwt.app.member.controller;


import com.example.rest_api_with_jwt.app.base.dto.RsData;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginReq;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginRes;
import com.example.rest_api_with_jwt.app.member.entity.Member;
import com.example.rest_api_with_jwt.app.member.service.MemberService;
import com.example.rest_api_with_jwt.app.security.entity.MemberContext;
import com.example.rest_api_with_jwt.util.Util;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "MemberController", description = "로그인 기능과 로그인 된 회원의 정보를 제공 기능을 담당하는 컨트롤러")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberContext memberContext) {

        return "안녕" + memberContext;
    }

    @GetMapping("/me")
    public ResponseEntity<RsData> me(@AuthenticationPrincipal MemberContext memberContext) {
        if (memberContext == null) { // 임시코드, 나중에는 시프링 시큐리티를 이용해서 로그인을 안했다면, 아예 여기로 못 들어오도록
            return Util.spring.responseEntityOf(RsData.failOf(null));
        }

        return Util.spring.responseEntityOf(RsData.successOf(memberContext));
    }


    @PostMapping("/login")
    public ResponseEntity<RsData> login(@Valid @RequestBody PostLoginReq postLoginReq) {
        {
            if (postLoginReq.isNotValid()) {
                return Util.spring.responseEntityOf(RsData.of("F-1", "로그인 정보가 올바르지 않습니다."));
            }
            Member member = memberService.findByUsername(postLoginReq.getUsername()).orElse(null);

            if (member == null) {
                return Util.spring.responseEntityOf(RsData.of("F-2", "일치하는 회원이 존재하지 않습니다."));
            }
            if (passwordEncoder.matches(postLoginReq.getPassword(), member.getPassword()) == false) {
                return Util.spring.responseEntityOf(RsData.of("F-3", "비밀번호가 일치하지 않습니다."));
            }
            String accessToken = "JWT_Access_Token";

            PostLoginRes postLoginRes = memberService.Login(postLoginReq);
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "S-1",
                            "로그인 성공, Access Token을 발급합니다.",
                            Util.mapOf(
                                    "accessToken", postLoginRes.getAccessToken()
                            )
                    ),
                    Util.spring.httpHeadersOf("Authentication", accessToken)
            );
        }

    }
}
