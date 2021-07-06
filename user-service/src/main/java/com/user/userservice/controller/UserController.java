package com.user.userservice.controller;

import com.user.userservice.entity.AuthRequest;
import com.user.userservice.model.User;
import com.user.userservice.service.UserService;
import com.user.userservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_GENERAL') or hasAuthority('ROLE_ADMIN')")
    public String greeting(){
        return "Hello, Welcome to User Service.";
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
           );
       }catch (Exception e){
           throw new Exception("Invalid username/password");
       }

       return  jwtUtil.generateToken(authRequest.getEmail());
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody User user){
        Boolean bool=userService.createUser(user);
        if(!bool){
            return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @PutMapping("/role/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Boolean> updateMyRole(@PathVariable Integer id){
        Boolean bool=userService.updateRole(id);
        if(!bool){
            return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
