package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.example.demo.util.customUserDetails.CustomUserDetails;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenProvider {
    //AccessToken 생성
    public String createAccessToken(Authentication authentication) {
        log.info("Creating access_token...");
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256("SOMESECRET".getBytes());


        return JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                //.withClaim("roles", authResult.getAuthorities().stream().map(r -> new SimpleGrantedAuthority("ROLE_"+r)).collect(Collectors.toList()))
                .withClaim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
