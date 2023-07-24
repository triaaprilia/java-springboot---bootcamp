package com.bootcamp.test.api.service;

import com.bootcamp.test.api.dao.CategoryDao;
import com.bootcamp.test.api.dto.CategoryDto;
import com.bootcamp.test.api.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDao dao;

    public void save(CategoryDto.Save data) {this.dao.save(data);}

    public List<Category> findAll(){return this.dao.findAll();}

    public Category findById(Integer id){
        return this.dao.findByid(id).orElseThrow(() -> new RuntimeException("Categori dengan id" + id +"tidak ditemukan"));
    }

    public void delete(Integer id) {this.dao.delete(id);}

    public void update(Integer id, CategoryDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
