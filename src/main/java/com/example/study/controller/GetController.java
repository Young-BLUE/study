package com.example.study.controller;

import com.example.study.medel.SearchParam;
import com.example.study.medel.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // LocalHost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") //LocalHost:8080/api/getMethod 가 아래에 요청으로
    public String getRequest(){
        return "Hi getMethod";
    }

    //Localhost:8080/api/getParameter  주소매칭 / 메소드를 지정하지 않아도 됨 주소만 설정
    @GetMapping("/getParameter")    //Localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbbb";  // Request 변수로 들어올 수 있는 것에 대해서는 지역번수를 사용하지 않는다
        // 주소창에서는 password 라고 들어온 항목에 대해서 pwd로 매칭이 되어 결과값이 나옴 (RequestParam의 name 지정 방법)

        System.out.println("id : " + id);
        System.out.println("pwd : " + password);

        return id+pwd;
    }

    //Locahost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    // 직접 검색 파라미터를 받는 방법
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());


        //{ "account" : "", "email" : "", "page" : 0}  --> json 형태
        return searchParam;
        // 객체를 리턴하게되면 자체에 내장되어있는 Json Library를 통해 객체로 반환됨
    }

    @GetMapping("/header")
    public Header getHeader(){  // Header 라는 object를 return 하여 json 형태로 나오게 될 것
        // {"resultCode" : "OK" , "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
