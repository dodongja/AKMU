package com.example.demo.application.user;

import com.example.demo.web.user.dto.UserRequest;
import com.example.demo.dto.response.DuplicationCheck;
import com.example.demo.domain.board.user.entity.User;

public interface UserService {
    public DuplicationCheck register (UserRequest memberRequest);
    public UserRequest login (UserRequest memberRequest);
    public void modify (UserRequest memberRequest);
    public void remove (User member);

}
