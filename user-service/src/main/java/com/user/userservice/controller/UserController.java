package com.user.userservice.controller;

import com.user.userservice.model.User;
import com.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String greeting(){
        return "Hello, Welcome to User Service.";
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody User user){
        return new ResponseEntity<Boolean>(this.userService.loginUser(user.getEmail(),user.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody User user){
        Boolean bool=userService.createUser(user);
        if(!bool){
            return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
