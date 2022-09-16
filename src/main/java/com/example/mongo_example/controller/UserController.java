package com.example.mongo_example.controller;

import com.example.mongo_example.model.User;
import com.example.mongo_example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<List<User>> read(){
        List<User> all = userRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> readBy(@PathVariable ("id")String id){
        return new ResponseEntity<>(userRepository.findById(id).get(),HttpStatus.FOUND);



    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable String id,@RequestBody User user){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            return null;
        }
        User users = byId.get();
        if (user.getName()!=null){
            users.setName(user.getName());
        }
        if (user.getSurname()!=null){
            users.setSurname(user.getSurname());
        }
        return new ResponseEntity<>(userRepository.save(users),HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") String id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }



    }



}
