package com.bootcamp.test.api.controller;

import com.bootcamp.test.api.dto.ProductDto;
import com.bootcamp.test.api.entity.Product;
import com.bootcamp.test.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = this.service.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(
            @PathVariable(name = "id") Integer id
    ){
        Product product = this.service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody ProductDto.Save data
    ){
        this.service.save(data);
        return ResponseEntity.ok("data berhasil diinsert");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id
    ){
        this.service.delete(id);
        return ResponseEntity.ok("data berhasil dihapus");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody ProductDto.Save data
    ){
        try{
            this.service.update(id, data);
            return ResponseEntity.ok("data berhasil diupdate");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data dengan " + id + " tidak ditemukan");
        }
    }
}
