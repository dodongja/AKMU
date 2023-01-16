package com.example.demo.util.customUserDetails;

import com.example.demo.domain.user.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//security가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인 진행이 완료가 되면 시큐리티 session을 만들어 준다 (Security ContextHolder)
//오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 됨
// User 오브젝트타입 => UserDetails 타입 객체
//기본 login 회원 정보는 UserDetails에 들어가고 OAuth login 정보는 OAuth2User 객체에 정보가 들어감
// 그래서 커스텀 유저 디테일에 두개를 implements해서 어떤 로그인이든 커스텀유저디테일로 정보를 가져 올 수 있도록 함
@Data
public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> role = new ArrayList<>();
            role.add(new SimpleGrantedAuthority(getUsername()));
        return role;

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
