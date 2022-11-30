//package com.example.demo.entity.board.photoBoard;
//
//import com.example.demo.domain.user.entity.User;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Setter
//@Getter
//@Entity
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class PhotoBoardLike {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long likeNo;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name= "board_no")
//    private PhotoBoard photoBoard;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name= "user_id")
//    private User user;
//
//    @CreationTimestamp
//    private Date regDate;
//
//    @UpdateTimestamp
//    private Date updDate;
//}
