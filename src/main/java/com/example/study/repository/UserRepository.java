package com.example.study.repository;

import com.example.study.medel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { // GenericValue, PK 의 자료형












    /*
    // query를 method로 사용한다는 의미에서 query method 라고도 함
    // select * from user where account = ? << test03, test04 등등
    Optional<User> findByAccount(String account);  // 변수명과는 상관 없이 매칭되지만 동일하게 맞추는 것을 추천


    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);

     */

}


