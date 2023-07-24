package com.bootcamp.test.api.service;

import com.bootcamp.test.api.dao.UserProductDao;
import com.bootcamp.test.api.dto.UserProductDto;
import com.bootcamp.test.api.entity.UserProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserProductDao dao;

    public void save(UserProductDto.Save data){ this.dao.save(data);}

    public List<UserProduct> findAll(){
        return this.dao.findAll();
    }

    public UserProduct findById(Integer id){
        return this.dao.findById(id).orElseThrow(() -> new RuntimeException("UserProduct dengan id" + id + "tidak ditemukan"));
    }

    public void delete(Integer id){
        this.dao.delete(id);
    }

    public void update(Integer id, UserProductDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
