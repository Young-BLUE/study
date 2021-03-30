package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.medel.entity.User;
import com.example.study.medel.network.Header;
import com.example.study.medel.network.request.UserApiRequest;
import com.example.study.medel.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")  // 추후 Enum 으로 관리 예정
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById
        // user -> userApiResponse return
        return userRepository.findById(id)  // id가 있으면 response를 통해 return하고 없을시 orElseGet을 통해 ERROR 반환
                .map(user -> response(user))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터를 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> { // user가 있다면 값을 set  / map은 새로운 object를 return
            // 3. data -> update
            // id
            user.setAccount(userApiRequest.getAccount())  // Acceessors 를 통해 다음과 같이 연속적으로 가능
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })// 4. userApiResponse
                .map(user -> userRepository.save(user))     // 값이 새로 update된 user object를 저장 -> newUser
                .map(user -> response(user))    // userApiResponse
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override // path parameter (/api/user/{id})
    public Header delete(Long id) {
        // 1. id -> repository -> user

        Optional<User> optional = userRepository.findById(id);

        // 2. repository -> delete

        return optional.map(user -> {
            userRepository.delete(user);
            // 3. response return
            return Header.OK();
        })
                .orElseGet(()-> Header.ERROR("데이터 없음"));



    }

    private Header<UserApiResponse> response(User user){  // 다른 CRUD 부분에서도 사용하기 때문에 중복을 막기위해 Method화
        // user -> userApiResponse Method를 만들어서 return

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())  // todo 암호와, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);
    }
}
