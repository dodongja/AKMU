package com.example.demo.repository.member;

import com.example.demo.entity.member.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;


public interface MemberAuthRepository extends JpaRepository<Role, Long> {


    @Query("select ma.auth from MemberAuth ma where ma.memberNo = :memberNo")
    Optional<Role> findByMemberNo(@Param("memberNo") Long memberNo);
}
