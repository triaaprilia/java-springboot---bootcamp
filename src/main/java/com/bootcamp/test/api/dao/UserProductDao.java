package com.bootcamp.test.api.dao;

import com.bootcamp.test.api.dto.ProductDto;
import com.bootcamp.test.api.dto.UserProductDto;
import com.bootcamp.test.api.dto.UsersDto;
import com.bootcamp.test.api.entity.UserProduct;
import com.bootcamp.test.api.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public void save (UserProductDto.Save inputdata){
        String query = "INSERT INTO public.user_product\n" +
                "(user_id, product_id, quantity)\n" +
                "VALUES(:user_id, :product_id, :quantity);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user_id", inputdata.getUser_id());
        map.addValue("product_id", inputdata.getProduct_id());
        map.addValue("quantity", inputdata.getQuantity());

        this.jdbcTemplate.update(query, map);
    }

    public List<UserProduct> findAll(){
        String query ="SELECT id, user_id, product_id, quantity\n" +
                "FROM public.user_product;\n";
        return this.jdbcTemplate.query(query, new RowMapper<UserProduct>() {
            @Override
            public UserProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserProduct userProduct = new UserProduct();
                userProduct.setId(rs.getInt("id"));
                userProduct.setUser_id(rs.getInt("user_id"));
                userProduct.setProduct_id(rs.getInt("product_id"));
                userProduct.setQuantity(rs.getInt("quantity"));
                return userProduct;
            }
        });
    }

    public Optional<UserProduct> findById(Integer id){
        String query ="SELECT id, user_id, product_id, quantity\n" +
                "FROM public.user_product where id=:id\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        try{
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<UserProduct>>() {
                @Override
                public Optional<UserProduct> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserProduct userProduct = new UserProduct();
                    userProduct.setId(rs.getInt("id"));
                    userProduct.setUser_id(rs.getInt("user_id"));
                    userProduct.setProduct_id(rs.getInt("product_id"));
                    userProduct.setQuantity(rs.getInt("quantity"));
                    return Optional.of(userProduct);
                }
            });
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void delete(Integer id){
        String query ="DELETE FROM public.user_product\n" +
                "WHERE id=:id\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id", id);
            this.jdbcTemplate.update(query, map);
    }

    public void update(Integer id, UserProductDto.Save inputData){
        String query ="UPDATE public.user_product\n" +
            "SET user_id=0, product_id=0, quantity=0\n" +
            "WHERE id=:id\n";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("user_id", inputData.getUser_id());
        map.addValue("product_id", inputData.getProduct_id());
        map.addValue("quantity", inputData.getQuantity());

        this.jdbcTemplate.update(query, map);
    }
}
