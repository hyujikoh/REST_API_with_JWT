package com.example.rest_api_with_jwt.app.member.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class PostLoginReq {
    @NotEmpty(message = "username 을(를) 입력해주세요.")
    private String username;
    @NotEmpty(message = "password 을(를) 입력해주세요.")
    private String password;

}
