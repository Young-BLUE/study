package com.example.study.medel.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class) //LoginUserAuditorAware의 AdminServer를 Return 하게됨
@Builder  // 객체 생성에 사용
@Accessors(chain = true)  // 객체 업데이트에 사용
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String role;

    private LocalDateTime lastLoginAt;

    private LocalDateTime passwordUpdatedAt;

    private int loginFailCount;

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
}
