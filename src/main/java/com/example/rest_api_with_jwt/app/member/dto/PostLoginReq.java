package com.example.rest_api_with_jwt.app.member.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLoginReq {
    @NotEmpty(message = "username 을(를) 입력해주세요.")
    private String username;
    @NotEmpty(message = "password 을(를) 입력해주세요.")
    private String password;
    public boolean isNotValid() {
        return username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0;
    }
}
