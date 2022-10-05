package com.example.rest_api_with_jwt.app.member.controller;


import com.example.rest_api_with_jwt.app.member.dto.PostLoginReq;
import com.example.rest_api_with_jwt.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value="/member/login")
    public void Login(@RequestBody PostLoginReq postLoginReq){
        memberService.Login(postLoginReq);
    }

}
