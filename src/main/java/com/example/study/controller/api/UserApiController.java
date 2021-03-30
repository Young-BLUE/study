package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.medel.network.Header;
import com.example.study.medel.network.request.UserApiRequest;
import com.example.study.medel.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {
// Interface 상속을 통해 누락될 수 있는 부분을 강제적으로 가이드 해줌
// Generic Type을 통해 관리

    @Autowired
    private UserApiLogicService userApiLogicService;


    @Override
    @PostMapping("")   // /api/user로 맵핑됨
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        // 실제 서비스에서는 콘솔창이 아닌 따로 파일에 log를 남겨서 확인하게됨
        log.info("{}",request);  // Slf4j 어노테이션을 통해서 사용 / request가 toString 형태로 기록됨
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")  // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read id : {}" + id );
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")  // api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")  // api/user/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
