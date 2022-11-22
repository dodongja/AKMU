package com.example.demo.controller.member;

import com.example.demo.dto.request.MemberRequest;
import com.example.demo.dto.response.DuplicationCheck;
import com.example.demo.entity.member.User;
import com.example.demo.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/register")
    public DuplicationCheck memberRegister(@Validated @RequestBody MemberRequest memberRequest){
        log.info("memberRegister request" + memberRequest);


        return memberService.register(memberRequest);

    }

    @PostMapping("/login")
    public MemberRequest memberLogin (@RequestBody MemberRequest memberRequest) {
        log.info("MemberLogin()" + memberRequest);

        MemberRequest memberResponse = memberService.login(memberRequest);

        if (memberResponse != null){
            log.info("Login Success");
        }else{
            log.info("Login Fail");
        }
        return memberResponse;
    }

    @PutMapping("/modify")
    public MemberRequest memberInformationModify (@RequestBody MemberRequest memberRequest) {
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
