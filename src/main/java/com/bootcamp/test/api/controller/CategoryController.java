package com.bootcamp.test.api.controller;

import com.bootcamp.test.api.dto.CategoryDto;

import com.bootcamp.test.api.entity.Category;
import com.bootcamp.test.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = this.service.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(
            @PathVariable(name = "id") Integer id
    ){
        Category category = this.service.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody CategoryDto.Save data
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
            @RequestBody CategoryDto.Save data
    ){
        try{
            this.service.update(id, data);
            return ResponseEntity.ok("data berhasil diupdate");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data dengan" + id + "tidak ditemukan");
        }
    }
}
