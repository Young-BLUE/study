package com.example.study.medel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"partnerList"}) // 배열 형태시 여러건 적용, 그 반대는 하나만 적용
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String title;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // Category 1 : N Partner
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category") // category 변수와 매칭시키겠다
    private List<Partner> partnerList;
}
