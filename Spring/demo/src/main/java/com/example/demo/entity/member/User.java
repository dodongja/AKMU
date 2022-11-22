package com.example.demo.entity.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 15,
            nullable = false,
            unique = true)
    private String email;

    @Column(length = 256, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime updDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_no")
    private List<Role> role;


    public User(Long id, String password, String nickname, String email) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public User(Long id, String password, Role auth) {
        this.id = id;
        this.password = password;

        if(auth != null){
            changeAuth(auth);
        }
    }

    public void changeAuth (Role auth){}

    /*public void addAuth (Role auth) {
        if(authList == null) {
            authList = new ArrayList<>();
        }

        authList.add(auth);
    }

    public void clearAuthList() {
        authList.clear();
    }*/


}
