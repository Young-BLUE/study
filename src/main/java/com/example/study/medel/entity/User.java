package com.example.study.medel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity  // == table
//@Table(name = "user") 클래스의 이름과 테이블의 이름이 서로 동일하다면 선언하지 않아도 자동맵핑됨
@ToString(exclude = {"orderGroup"})
public class User {  // DB Table과 이름이 동일하게 (Upper Camel Case)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 관리 방식에 따른 전략
    private Long id;

    //@Column(name = "account")  변수명과 컬럼명이 같으면 자동 맵핑
    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;


    // (User) 1 :  N (OrderGroup)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
    // 서로 상호참조시 Lombok 에서 ToString이 overflow됨









    // 1 : N  User 입장에서는 user가 1이고 OrderDetail이 N
    /*@OneToMany(fetch = FetchType.LAZY , mappedBy = "user")  // 해당 user는 OrderDetail의 user 객체 (변수명)
    private List<OrderDetail> orderDetailList;  // OrderDetail 이라는 클래스 안의 user라는 변수에 맵핑 시키겠다는 의미


    */

}
