package com.bootcamp.test.api.dao;

import com.bootcamp.test.api.dto.ProductDto;
import com.bootcamp.test.api.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save (ProductDto.Save inputdata){
        String query = "INSERT INTO public.products\n" +
                "(\"name\", category_id, stock)\n" +
                "VALUES(:name, :category_id, :stock);\n";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", inputdata.getName());
        map.addValue("category_id", inputdata.getCategory_id());
        map.addValue("stock", inputdata.getStock());

        this.jdbcTemplate.update(query,map);
    }

    public List<Product>findAll(){
        String query = """
                SELECT p.id, p.name, p.category_id, p.stock, c.name as categoryName
                FROM public.products p
                Join category c on  c.id = p.category_id""";

        return this.jdbcTemplate.query(query, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("categoryName"));
                product.setStock(rs.getInt("stock"));
                return product;
            }
        });
    }
    public Optional<Product> findById(Integer id){
        String query = "SELECT id, \"name\", category_id, stock\n" +
                "FROM public.products where id =:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Product>>() {
                @Override
                public Optional<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product =  new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setStock(rs.getInt("stock"));
                    return Optional.of(product);
                }
            });
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void delete(Integer id){
        String query = "DELETE FROM public.products\n" +
                "WHERE id=:id\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id", id);
            this.jdbcTemplate.update(query, map);
    }

    public void update(Integer id, ProductDto.Save inputData){
        String query ="UPDATE public.products\n" +
                "SET \"name\"=:name, category_id=:category_id, phone=:phone\n" +
                "WHERE id=:id);\n";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("name", inputData.getName());
        map.addValue("category_id", inputData.getCategory_id());
        map.addValue("stock", inputData.getStock());

        this.jdbcTemplate.update(query, map);
    }
}
