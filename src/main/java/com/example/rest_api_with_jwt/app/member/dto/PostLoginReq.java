package com.example.rest_api_with_jwt.app.member.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLoginReq {
    private String username;
    private String password;
}
