package com.example.demo.application.user;

import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.jwt.TokenProvider;
import com.example.demo.web.user.dto.UserRequest;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @param userRequest
     * @return
     * 중복 체크하고 회원 가입 되어야 함    */
    @Override
    public String register(UserRequest userRequest) {

        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

        if(userRepository.existsByEmail(userRequest.getEmail())){
            return "이메일 중복 입니다";
        }

        if(userRepository.existsByNickname(userRequest.getNickname())){
            return "닉네임 중복 입니다";
        }

        User user = User.builder()
                .email(userRequest.getEmail())
                .role(Role.USER)
                .nickname(userRequest.getNickname())
                .password(encodedPassword)
                        .build();


        userRepository.save(user);

        return "가입 되었습니다";
        }

    @Override
    public UserRequest login(UserRequest userRequest) {
        /*
        <Spring Security>
        1. login request
        2. AuthenticationFilter가 request 가로 챔. 이 때 가로챈 정보를 통해 UsernamePasswordAuthenticationToken 객체(미검증 상태) 생성
        3. ProviderManager(AuthenticationManger 구현체)에게 객체를 전달
        4. AuthenticationProvider에 객체 전달
        5. 실제 DB로부터 사용자 인증 정보를 가져오는 UserDetailService에 사용자 정보를 넘겨줌
        6. 넘겨 받은 정보를 통해 DB에서 찾은 사용자 정보인 UserDetails 객체를 생성
        7. AuthenticationProvider는 UserDetails를 넘겨받고 사용자 정보를 비교
        8. 인증이 완료되면 , 사용자 정보를 담은 Authentication 객체를 반환
        9. 최초의 AuthenticaitonFilter에 Autehntication 객체가 반환 됨
        10. Authentication 객체를 SecurityContext에 저장
         */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getEmail(),
                        userRequest.getPassword()
                )
        );

        log.info("Set authentication");
        SecurityContextHolder.getContext().setAuthentication(authentication); //1 ~10이 여기서 이루어지냐?

        String access_token = tokenProvider.createAccessToken(authentication);
        String refresh_token = tokenProvider.createRefreshToken(authentication);
    }
    /*
    @Override
    public void modify(UserRequest memberRequest) {
            Optional<User> maybeMember =memberRepository.findByUserId(memberRequest.getUserId());
            User memberInfo =  maybeMember.get();

            String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());
            memberInfo.setPassword(encodedPassword);

            memberRepository.save(memberInfo);
    }

    @Override
    public void remove(User member) {
            Optional<User> maybeMember = memberRepository.findByUserId(member.getUserId());
            User removeMember = maybeMember.get();
            memberRepository.deleteById(removeMember.getMemberNo());
    }*/
}
