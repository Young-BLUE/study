package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.medel.entity.Item;
import com.example.study.medel.network.Header;
import com.example.study.medel.network.request.ItemApiRequest;
import com.example.study.medel.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository; // 객체로 하단 내용에 삽입

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();
        // Code Refactoring 할 때에 Not null Check 부분 넣을 예정

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        Item newItem = itemRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("아이템 데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();

        return itemRepository.findById(body.getId())
                .map(entityItem -> {
                    entityItem
                            .setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());
                    return entityItem;
                })
                .map(newEnityItem -> itemRepository.save(newEnityItem))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

       return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();  // map은 return 값이 있어야하지만 delete는 void 이기 때문에 OK라는 return을 작성
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    private Header<ItemApiResponse> response(Item item){
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);
    }

}
