package com.example.rest_api_with_jwt.app.member.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLoginReq {
    private String username;
    private String password;

    public boolean isNotValid() {
        return username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0;
    }
}
