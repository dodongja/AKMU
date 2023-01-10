package com.example.demo.domain.user.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;




@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 30,
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role",
            columnDefinition = "varchar(20)")
    private Role role;

    @Builder
    public User(String email,  String password, Role role, String nickname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
    }


}
