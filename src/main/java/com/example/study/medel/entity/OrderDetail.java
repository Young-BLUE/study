package com.example.study.medel.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity  // order_detail 로 자동 연결됨 (Camel Case와 snake_case)
//@ToString(exclude = {"user","item"})  // user와 item이 서로 상호참조를 하기 때문에 lombok의 ToString이 충돌. 그것을 방지위해 제외
@ToString(exclude = {"orderGroup","item"})
@EntityListeners(AuditingEntityListener.class) //LoginUserAuditorAware의 AdminServer를 Return 하게됨
@Builder  // 객체 생성에 사용
@Accessors(chain = true)  // 객체 업데이트에 사용
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy //LoginUserAuditorAware의 AdminServer를 Return 하게됨
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy //LoginUserAuditorAware의 AdminServer를 Return 하게됨
    private String updatedBy;



    // OrderDetail N : 1 Item
    @ManyToOne
    private Item item;


    // OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup; // mappedBy의 변수명과 일치화










    /*
    // N : 1 OrderDetail의 입장에서 자신은 N 상대는 1 (유저)
    @ManyToOne  // 연관관계 설정시 반드시 객체 이름으로 설정 해주어야함
    private User user;  // Long 에서 User 타입으로 변경, userId -> user로 변경 (객체 이름으로) user_id

    // N : 1
    @ManyToOne
    private Item item;
    */
}
