package com.bootcamp.test.api.service;

import com.bootcamp.test.api.dao.ProductDao;
import com.bootcamp.test.api.dto.ProductDto;
import com.bootcamp.test.api.entity.Product;
import com.bootcamp.test.api.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao dao;
    private final CategoryService categoryService;

    public void save(ProductDto.Save data) {
        categoryService.findById(data.getCategory_id());
        this.dao.save(data);}

    public List<Product> findAll(){return this.dao.findAll();}

    public Product findById(Integer id){
        return this.dao.findById(id).orElseThrow(() ->
                new IdNotFoundException("Produk dengan id " + id +" tidak ditemukan"));
    }

    public void delete(Integer id) {this.dao.delete(id);}
    public void update(Integer id, ProductDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
