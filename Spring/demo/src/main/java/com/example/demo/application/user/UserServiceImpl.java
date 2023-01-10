package com.example.demo.application.user;

import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.web.user.dto.UserRequest;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    /*@Override
    public UserRequest login(UserRequest memberRequest) {
            Optional<User> maybeMember = memberRepository.findByUserId(memberRequest.getUserId());

            if(maybeMember.equals(Optional.empty())){
                log.info("There are no person who has this id!");
                return null;
            }

            User loginMember =maybeMember.get();

            if(!passwordEncoder.matches(memberRequest.getPassword(), loginMember.getPassword())) {
                log.info("Entered wrong password!");
                return null;
            }

            Optional<Role> maybeMemberAuth =
                memberAuthRepository.findByMemberNo(loginMember.getMemberNo());

            if (maybeMemberAuth.equals(Optional.empty())) {
                log.info("no auth");
                return null;
            }

            Role memberAuth = maybeMemberAuth.get();
            UserRequest response = new UserRequest(loginMember.getUserId(), null,
                    loginMember.getNickname(), loginMember.getEmail(), memberAuth.getAuth());

            return response;
    }

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
