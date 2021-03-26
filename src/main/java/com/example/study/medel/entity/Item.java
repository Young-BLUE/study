package com.example.study.medel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    private Integer price;

    private String content;


    // ** LAZY = 지연로딩 (연관된 테이블에 대해 조회 x) = Select * From item Where id = ?
    // 여러가지 데이터가 로딩이 될 수 있는 경우에는 LAZY 타입 사용


    // ** EAGER = 즉시 연관된 것을 모두 로딩 (join을 통해서 - 데이터가 많은 경우 EAGER 타입 사용시 시간, 오류 가능성 높아짐)
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // where item.id = ?
    // EAGER는 1:1 일 경우에 적합

    // 1 : N    Item 입장에서는 1이고 OrderDetail이 N
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "item")   // OrderDetail의 item에 매칭
    private List<OrderDetail> orderDetailList;
}
