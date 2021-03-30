package com.example.study.medel.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> { // Generic 형식으로 이용

    // api 통신시간
    //@JsonProperty("transaction_time")  // 직접 json 변환시 해당 이름으로 처리됨
    private LocalDateTime transactionTime;  // ISO, YYYY-MM-DD HH(hh):mm:ss 등등이 있음

    // api 응답코드
    private String resultCode;

    // api 부가설명
    private String description;


    private T data;


    // OK (정상)
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()  // 형변환
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // Data (데이터 통신)
    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()  // 형변환
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // Error (비정상)
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>) Header.builder()  // 형변환
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();


    }
}
