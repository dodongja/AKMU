package com.example.demo.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;



@Getter
@RequiredArgsConstructor
public enum Role {
    //role : ROLE_USER , ROLE_ADMIN
    USER("Role_USER", "유저"),
    ADMIN("Role_ADMIN", "관리자");

    private final String key;
    private final String title;


}
