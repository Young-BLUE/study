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
@Entity
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class) //LoginUserAuditorAware의 AdminServer를 Return 하게됨
@Builder  // 객체 생성에 사용
@Accessors(chain = true)  // 객체 업데이트에 사용
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

    private String brandName;

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


    // Item N : 1 Partner
    @ManyToOne
    private Partner partner;

    // Item 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;









    /*
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
    */
}
