package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.medel.entity.Item;
import com.example.study.medel.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    // Dependency Injection (DI)
    @Autowired // 키워드 이용하여 new userRepository와 같은 인스턴스 생성이 따로 필요 없어짐
    private UserRepository userRepository;

    @Test
    public void create(){

        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);






        /*
        // String sql = insert into user (%s, %, %d ) value (account, email, age); ... 등과 같은 내용을 썼었지만
        // JPA를 통해 간편하게 관리 가능
        User user = new User();  // 직접 생성하여 관리 Single ton pattern 형식
        //user.setId();  SQL에서 AI 체크하였기 때문에 순차적 자동생성됨
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-3333-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");


        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);  // User에서 lombok의 @Data를 통해 Object 를 toString 하여 보여줌
        */
    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("------------------주문묶음------------------");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령주소 : " + orderGroup.getRevAddress());
            System.out.println("총수량 : " + orderGroup.getTotalQuantity());
            System.out.println("총금액 : " + orderGroup.getTotalPrice());

            System.out.println("------------------주문상세------------------");
            orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                // 연결되어있는 객체를 . 을 통해 연결하여 정보를 가져올 수 있음
                System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle() );
                System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter() );
                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착 예정일자 : " + orderDetail.getArrivalDate());


            });
        });

        Assert.assertNotNull(user);




        /*
        // select * from user where id = ?
        Optional<User> user = userRepository.findByAccount("TestUser03"); // Long으로 선언했기 때문에 직접 타이핑 시 L을 붙여준다, select 하기 떄문에 User user


        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);

            });
        });
        */
    }

    @Test
    public void update(){








       /*
        Optional<User> user = userRepository.findById(2L);
        
        
        // create 에서는 신규 유저가 반환되지만, findById를 통해 해당 아이디가 존재할 경우 처리이기 때문에 해당 객체에 다시 반환됨
        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
        */
    }

    //@DeleteMapping("/api/user")
    //public void delete(@RequestParam Long id){ 다음과 같은 방식으로 한다면 입력받는 대상에 대해서 처리
    @Test
    @Transactional  // Springframework 에 있는 해당 내용을 입력해주면 마지막 자료라면 삭제 하더라도 다시 Roll Back 해준다
    public void delete(){









        /*
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent());  // 반드시 ID가 2번인 대상이 있어야 한다는 메소드 (해당 값을 통과해서 delete가 이루어져야함)
        // 통과하지 못할 경우 error occurred.

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser); // Delete는 DataRow를 삭제 했기 때문에 반환이 없음 (void)
        });



        Optional<User> deleteuser = userRepository.findById(3L);

        /*if(deleteuser.isPresent()){
            System.out.println("데이터 존재 : " + deleteuser.get());
        }else{
            System.out.println("***데이터가 삭제되어 존재하지 않습니다***");
        }

        Assert.assertFalse(deleteuser.isPresent()); // Delete 되었기 때문에 반드시 False값
        */
    }
}
