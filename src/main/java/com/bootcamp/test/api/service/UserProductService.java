package com.bootcamp.test.api.service;

import com.bootcamp.test.api.dao.UserProductDao;
import com.bootcamp.test.api.dto.UserProductDto;
import com.bootcamp.test.api.entity.Product;
import com.bootcamp.test.api.entity.UserProduct;
import com.bootcamp.test.api.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserProductDao dao;
    private final UserService userService;
    private final ProductService productService;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(UserProductDto.Save data){
        userService.findById(data.getUser_id());
        productService.findById(data.getProduct_id());
        Product product = productService.findById(data.getProduct_id());
        int quantity = data.getQuantity();
        if (product.getStock()>= quantity){
            int SisaStock = product.getStock() - quantity;
            String query = "UPDATE public.products\n" +
                    "SET stock=:stock\n" +
                    "WHERE id=:id\n";
            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("stock", SisaStock);
            map.addValue("id", product.getId());

            this.jdbcTemplate.update(query, map);
            this.dao.save(data);
            }else{
            throw new IllegalArgumentException("Stock kosong");
        }
        }


    public List<UserProduct> findAll(){
        return this.dao.findAll();
    }

    public UserProduct findById(Integer id){
        return this.dao.findById(id).orElseThrow(() ->
                new IdNotFoundException("UserProduct dengan id " + id + " tidak ditemukan"));
    }

    public void delete(Integer id){
        this.dao.delete(id);
    }

    public void update(Integer id, UserProductDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
