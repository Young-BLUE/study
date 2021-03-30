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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"partnerList"}) // 배열 형태시 여러건 적용, 그 반대는 하나만 적용
@EntityListeners(AuditingEntityListener.class) //LoginUserAuditorAware의 AdminServer를 Return 하게됨
@Builder  // 객체 생성에 사용
@Accessors(chain = true)  // 객체 업데이트에 사용
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String title;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy //LoginUserAuditorAware의 AdminServer를 Return 하게됨
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy //LoginUserAuditorAware의 AdminServer를 Return 하게됨
    private String updatedBy;

    // Category 1 : N Partner
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category") // category 변수와 매칭시키겠다
    private List<Partner> partnerList;
}
