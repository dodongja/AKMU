package com.example.demo.web.user;

import com.example.demo.web.user.dto.UserRequest;
import com.example.demo.dto.response.DuplicationCheck;
import com.example.demo.application.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService memberService;


    @PostMapping("/register")
    public DuplicationCheck memberRegister(UserRequest userRequest){
        log.info("memberRegister request" + userRequest);


        return memberService.register(userRequest);

    }

    @PostMapping("/login")
    public UserRequest memberLogin (@RequestBody UserRequest memberRequest) {
        log.info("MemberLogin()" + memberRequest);

        UserRequest memberResponse = memberService.login(memberRequest);

        if (memberResponse != null){
            log.info("Login Success");
        }else{
            log.info("Login Fail");
        }
        return memberResponse;
    }

    @PutMapping("/modify")
    public UserRequest memberInformationModify (@RequestBody UserRequest memberRequest) {
        log.info("memberModify(): " + memberRequest.getPassword());

        memberService.modify(memberRequest);

        return memberRequest;
    }

    /*@DeleteMapping("/remove")
    public void MemberInformationRemove(@RequestBody User member) {
        log.info("MemberRemove()" + member.getMemberNo());

        memberService.remove(member);
    }*/
}
