package com.example.study.medel.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity  // == table
//@Table(name = "user") 클래스의 이름과 테이블의 이름이 서로 동일하다면 선언하지 않아도 자동맵핑됨
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class) //LoginUserAuditorAware의 AdminServer를 Return 하게됨
@Builder  // 객체 생성에 사용
@Accessors(chain = true)  // 객체 업데이트에 사용
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

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy //LoginUserAuditorAware의 AdminServer를 Return 하게됨
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy //LoginUserAuditorAware의 AdminServer를 Return 하게됨
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
