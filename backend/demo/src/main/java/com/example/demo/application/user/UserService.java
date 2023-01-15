package com.example.demo.application.user;

import com.example.demo.web.user.dto.UserRequest;

public interface UserService {
    public String register (UserRequest memberRequest);
    public UserRequest login (UserRequest memberRequest);
    //public void modify (UserRequest memberRequest);
    //public void remove (User member);

}
