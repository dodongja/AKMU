//package com.example.demo.service.member;
//
//import com.example.demo.dto.request.MemberRequest;
//import com.example.demo.dto.response.DuplicationCheck;
//import com.example.demo.entity.member.User;
//import com.example.demo.entity.member.Role;
//import com.example.demo.repository.member.MemberAuthRepository;
//import com.example.demo.repository.member.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//
//
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class MemberServiceImpl implements MemberService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private MemberAuthRepository memberAuthRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//
//
//    @Override
//    public DuplicationCheck register(MemberRequest memberRequest) {
//
//            Optional<User> checkUserId = memberRepository.checkUserId(memberRequest.getUserId());
//            if(!checkUserId.equals(Optional.empty())) {
//                DuplicationCheck message = new DuplicationCheck("아이디 중복입니다");
//                return message;
//            }
//
//            Optional<User> checkNickname = memberRepository.checkNickname(memberRequest.getNickname());
//            if(!checkNickname.equals(Optional.empty())){
//                DuplicationCheck message = new DuplicationCheck("닉네임 중복입니다");
//                return message;
//            }
//
//            Optional<User> checkEmail = memberRepository.checkEmail(memberRequest.getEmail());
//            if(!checkEmail.equals(Optional.empty())){
//                DuplicationCheck message = new DuplicationCheck("이메일 중복입니다");
//                return message;
//            }
//
//            String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());
//            memberRequest.setPassword(encodedPassword);
//
//            Role authEntity = new Role(memberRequest.getAuth());
//            User memberEntity = new User(memberRequest.getUserId(), memberRequest.getPassword(),
//                    memberRequest.getNickname(),memberRequest.getEmail());
//
//            memberEntity.addAuth(authEntity);
//
//            memberRepository.save(memberEntity);
//
//            DuplicationCheck message = new DuplicationCheck("가입 되었습니다");
//
//            return message;
//        }
//
//    @Override
//    public MemberRequest login(MemberRequest memberRequest) {
//            Optional<User> maybeMember = memberRepository.findByUserId(memberRequest.getUserId());
//
//            if(maybeMember.equals(Optional.empty())){
//                log.info("There are no person who has this id!");
//                return null;
//            }
//
//            User loginMember =maybeMember.get();
//
//            if(!passwordEncoder.matches(memberRequest.getPassword(), loginMember.getPassword())) {
//                log.info("Entered wrong password!");
//                return null;
//            }
//
//            Optional<Role> maybeMemberAuth =
//                memberAuthRepository.findByMemberNo(loginMember.getMemberNo());
//
//            if (maybeMemberAuth.equals(Optional.empty())) {
//                log.info("no auth");
//                return null;
//            }
//
//            Role memberAuth = maybeMemberAuth.get();
//            MemberRequest response = new MemberRequest(loginMember.getUserId(), null,
//                    loginMember.getNickname(), loginMember.getEmail(), memberAuth.getAuth());
//
//            return response;
//    }
//
//    @Override
//    public void modify(MemberRequest memberRequest) {
//            Optional<User> maybeMember =memberRepository.findByUserId(memberRequest.getUserId());
//            User memberInfo =  maybeMember.get();
//
//            String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());
//            memberInfo.setPassword(encodedPassword);
//
//            memberRepository.save(memberInfo);
//    }
//
//    @Override
//    public void remove(User member) {
//            Optional<User> maybeMember = memberRepository.findByUserId(member.getUserId());
//            User removeMember = maybeMember.get();
//            memberRepository.deleteById(removeMember.getMemberNo());
//    }
//}
