package com.example.demo.web.user;

import com.example.demo.web.user.dto.UserRequest;
import com.example.demo.application.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> memberRegister(@RequestBody UserRequest userRequest){
        log.info("memberRegister request" + userRequest);
        ResponseEntity<Map<String, Object>> response = null;
        Map<String, Object> body = new HashMap<>();

        try {
            String message = userService.register(userRequest);
            body.put("message", message);
            response = new ResponseEntity<>(body, HttpStatus.OK);
        }catch (RuntimeException runtimeException){
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

   @PostMapping("/login")
    public UserRequest memberLogin (@RequestBody UserRequest userRequest) {
        log.info("MemberLogin()" + userRequest);

       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       userRequest.getEmail(),
                       userRequest.getPassword()
               )
       );

       SecurityContextHolder.getContext().setAuthentication(authentication);


    }
    /*
    @PutMapping("/modify")
    public UserRequest memberInformationModify (@RequestBody UserRequest memberRequest) {
        log.info("memberModify(): " + memberRequest.getPassword());

        userService.modify(memberRequest);

        return memberRequest;
    }
    */
    /*@DeleteMapping("/remove")
    public void MemberInformationRemove(@RequestBody User member) {
        log.info("MemberRemove()" + member.getMemberNo());

        memberService.remove(member);
    }*/
}
