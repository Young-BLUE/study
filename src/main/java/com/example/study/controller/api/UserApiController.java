package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.medel.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {
// Interface 상속을 통해 누락될 수 있는 부분을 강제적으로 가이드 해줌

    @Override
    @PostMapping("")   // /api/user로 맵핑됨
    public Header create() {
        return null;
    }

    @Override
    @GetMapping("{id}")  // /api/user/{id}
    public Header read(@PathVariable(name = "id") Long id) {
        return null;
    }

    @Override
    @PutMapping("")  // api/user
    public Header update() {
        return null;
    }

    @Override
    @DeleteMapping("{id}")  // api/user/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
