package com.Daniel.Controller;

import com.Daniel.JPA.UserRepository;
import com.Daniel.Model.User;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Logger;

@RestController
public class UserJPAController {
    Logger logger = Logger.getLogger(UserJPAController.class.getName());
    @Autowired
    private UserRepository repository;

    public UserJPAController(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/jpa/users")
    public List<User> findAll() {
        return repository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public User findOne(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        return user.get();
    }

    @PostMapping(value = "/jpa/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = repository.save(user);
//        URI location = MvcUriComponentsBuilder.fromMethodName(UserJPAController.class, "findOne", savedUser.getId()).buildAndExpand(savedUser.getId()).toUri();
        URI location = MvcUriComponentsBuilder.fromMethodName(UserJPAController.class, "findOne", savedUser.getId()).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", String.valueOf(location));
        return new ResponseEntity<User>(savedUser,headers, HttpStatus.OK);
//        return savedUser;
    }

    @PutMapping(value = "/jpa/users/{id}", consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestBody User userUpdated, @PathVariable int id){
        return repository.findById(id).map(user -> {
            user.setName(userUpdated.getName().isEmpty()?user.getName() : userUpdated.getName());
            user.setSurname(userUpdated.getSurname().isEmpty()?user.getSurname() : userUpdated.getSurname());
            user.setLogin(userUpdated.getLogin().isEmpty()?user.getLogin() : userUpdated.getLogin());
            user.setPassword(userUpdated.getPassword().isEmpty()? user.getPassword() : userUpdated.getPassword());
            repository.save(user);
            return user;
        }).orElseGet(()->{
            return repository.save(userUpdated);
        });
    }

    @DeleteMapping("/jpa/delete/{id}")
    public void deleteUser (@PathVariable int id){
        repository.deleteById(id);
    }




}
