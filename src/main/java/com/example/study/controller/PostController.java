package com.example.study.controller;

import com.example.study.medel.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
// Spring Boots는 메소드의 주소가 겹치면 실행되지 못하지만 클래스의 Mapping 주소가 같은 것은 상관 없다
public class PostController {

    // HTML <Form> 태그, ajax 검색 등에 사용됨 -> 검색 파라미터가 많다
    // http post body 에 data를 담아서 전송하겠다는 의미 (RequestBody)
    // json, xml, multipart-form / text-plain
    
    //@RequestMapping(method = RequestMethod.POST , path = "postMethod") 로 사용도 가능
    @PostMapping("/postMethod")
    public SearchParam SearchParam(@RequestBody SearchParam searchParam){

        return searchParam;
    }


    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }
}
