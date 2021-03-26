package com.example.study.medel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity  // order_detail 로 자동 연결됨 (Camel Case와 snake_case)
//@ToString(exclude = {"user","item"})  // user와 item이 서로 상호참조를 하기 때문에 lombok의 ToString이 충돌. 그것을 방지위해 제외
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;










    /*
    // N : 1 OrderDetail의 입장에서 자신은 N 상대는 1 (유저)
    @ManyToOne  // 연관관계 설정시 반드시 객체 이름으로 설정 해주어야함
    private User user;  // Long 에서 User 타입으로 변경, userId -> user로 변경 (객체 이름으로) user_id

    // N : 1
    @ManyToOne
    private Item item;
    */
}
