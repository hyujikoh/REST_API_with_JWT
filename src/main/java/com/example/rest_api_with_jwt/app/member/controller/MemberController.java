package com.example.rest_api_with_jwt.app.member.controller;


import com.example.rest_api_with_jwt.app.member.dto.PostLoginReq;
import com.example.rest_api_with_jwt.app.member.dto.PostLoginRes;
import com.example.rest_api_with_jwt.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value="/member/login")
    public ResponseEntity<PostLoginRes> Login(@RequestBody PostLoginReq postLoginReq){
        if(postLoginReq.isNotValid()){

            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        PostLoginRes postLoginRes = memberService.Login(postLoginReq);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", "JWT키");
        return new ResponseEntity<>(postLoginRes,headers, HttpStatus.OK);
    }

}
