package com.ecommerce.app.userservice.controller;

import com.ecommerce.app.userservice.model.postgres.PostgresUser;
import com.ecommerce.app.userservice.service.PostgresUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class PostgresUserController {
    private final PostgresUserService userService;

    @Autowired
    public PostgresUserController(PostgresUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PostgresUser> createUser(@RequestBody PostgresUser user){
        try{
            PostgresUser savedUser = userService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<PostgresUser>> getAllUsers(){
        List<PostgresUser> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostgresUser> getUserById(@PathVariable Long id){
        Optional<PostgresUser> user = userService.getUserById(id);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PostgresUser> updateUsr(@PathVariable Long id, @RequestBody PostgresUser updatedUser){
        try{
            PostgresUser user = userService.updateUser(id, updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try{
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
