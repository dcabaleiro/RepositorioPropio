package com.Daniel.Controller;

import com.Daniel.Bean.UserBean;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/jpa/users/{id}")
    @Retry(name ="estandar",fallbackMethod = "error")
    public ResponseEntity<UserBean> findOne(@PathVariable int id){
//        logger.info("Print log prueba");
        Map<String,Integer> uriVariables = new HashMap<>();
        uriVariables.put("id",id);
        ResponseEntity<UserBean> responseEntity = new RestTemplate().getForEntity("http://api-gateway:8765/UserMicroservice/jpa/users/{id}", UserBean.class, uriVariables);
        UserBean response = responseEntity.getBody();
//        return response;
        return responseEntity;
    }

    public ResponseEntity<UserBean> error(Exception ex){
        return new ResponseEntity<UserBean>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/jpa/users")
    public ResponseEntity<UserBean[]> findAll(){
        ResponseEntity<UserBean[]> response = new RestTemplate().getForEntity("http://api-gateway:8765/UserMicroservice/jpa/users/", UserBean[].class);
        return response;
    }

    @PostMapping(value = "/jpa/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserBean> createUser(@RequestBody UserBean user){
        UserBean userBean = new UserBean(user.getId(), user.getName(), user.getSurname(), user.getLogin(), user.getPassword());
        ResponseEntity<UserBean> response = new RestTemplate().postForEntity("http://api-gateway:8765/UserMicroservice/jpa/createUser",user,UserBean.class);
        HttpHeaders headers = new HttpHeaders();
        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class,"findOne",response.getBody().getId()).build().toUri();
        headers.add("location", String.valueOf(location));
        return new ResponseEntity<UserBean>(response.getBody(),headers,HttpStatus.OK);
    }

    @PutMapping(value="/jpa/users/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserBean> updateUser(@RequestBody UserBean userUpdated, @PathVariable int id){
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("id",id);
        RestTemplate restTemplate = new RestTemplate();
//        UserBean body = response.put("http://localhost:8001/jpa/users/{id}",userUpdated,uriVariables);
        HttpEntity<UserBean> requestEntity = new HttpEntity<UserBean>(userUpdated);
        HttpEntity<UserBean> response = restTemplate.exchange("http://api-gateway:8765/UserMicroservice/jpa/users/{id}", HttpMethod.PUT, requestEntity, UserBean.class, uriVariables);
        UserBean body = response.getBody();
        return new ResponseEntity<UserBean>(body,HttpStatus.OK);
    }

    @DeleteMapping(value = "/jpa/delete/{id}")
    public void delete(@PathVariable int id){
        Map<String, Integer> variablesURI = new HashMap<>();
        variablesURI.put("id",id);
        new RestTemplate().delete("http://api-gateway:8765/UserMicroservice/jpa/delete/{id}",variablesURI);
    }

}
