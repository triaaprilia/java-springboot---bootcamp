package com.bootcamp.test.api.controller;

import com.bootcamp.test.api.dto.UsersDto;
import com.bootcamp.test.api.entity.Users;
import com.bootcamp.test.api.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        List<Users> users = this.service.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(
            @PathVariable(name = "id") Integer id
    ){
        Users user = this.service.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody UsersDto.Save data
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
            @RequestBody UsersDto.Save data
    ){
        try{
            this.service.update(id, data);
            return ResponseEntity.ok("data berhasil diupdate");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data dengan" + id + "tidak ditemukan");
        }
    }
}

