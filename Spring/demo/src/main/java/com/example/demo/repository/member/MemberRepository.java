package com.example.demo.repository.member;

import com.example.demo.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

    public interface MemberRepository extends JpaRepository<User, Long> {

    @Query("select m from MemberInfo m where m.userId = :userId")
    Optional<User> findByUserId(@Param("userId") String userId);

    @Query("select m.userId from MemberInfo m where m.userId = :userId")
    Optional<User> checkUserId(@Param("userId") String userId);

    @Query("select m.nickname from MemberInfo m where m.nickname = :nickname")
    Optional<User> checkNickname(@Param("nickname") String nickname);

    @Query("select m.email from MemberInfo m where m.email = :email")
    Optional<User> checkEmail(@Param("email") String email);

}
