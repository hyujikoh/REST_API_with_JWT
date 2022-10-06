package com.example.rest_api_with_jwt.app.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLoginRes {
    private String username;
    private String accessToken;

}
