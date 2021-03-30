package com.example.study.ifs;

import com.example.study.medel.network.Header;

public interface CrudInterface<Req,Res> {  // Generic type으로 관리하여 다른 Controller 생성시에도 활용 가능

    // interface를 통해서 작성시 놓칠 수 있는 부분을 가이드 하는 역할을 하게됨
    Header<Res> create(Req request);  // todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Req request);

    Header delete(Long id);


}
