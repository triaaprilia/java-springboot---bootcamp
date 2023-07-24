package com.bootcamp.test.api.service;

import com.bootcamp.test.api.dao.UserDao;
import com.bootcamp.test.api.dto.UsersDto;
import com.bootcamp.test.api.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao dao;

    public void save(UsersDto.Save data){
        this.dao.save(data);
    }

    public List<Users> findAll(){
        return this.dao.findAll();
    }

    public Users findById(Integer id){
        return this.dao.findById(id).orElseThrow(() -> new RuntimeException("User dengan id" + id + "tidak ditemukan"));
    }

    public void delete(Integer id){
        this.dao.delete(id);
    }

    public void update(Integer id, UsersDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
