package com.Daniel.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public String users(){
        return "Prueba";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user")
    public String user(){
        int id;
        return "Prueba";
    }
}
