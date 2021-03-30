package com.example.study.ifs;

import com.example.study.medel.network.Header;

public interface CrudInterface {

    // interface를 통해서 작성시 놓칠 수 있는 부분을 가이드 하는 역할을 하게됨
    Header create();  // todo request object 추가

    Header read(Long id);

    Header update();

    Header delete(Long id);


}
