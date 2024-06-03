package com.chat.ciao.controllers;

import com.chat.ciao.auth.LoginRequest;
import com.chat.ciao.auth.RegisterRequest;
import com.chat.ciao.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
      return new ResponseEntity<>(this.userDetailsService.login(request), HttpStatus.OK);
    }

  @PostMapping("/sign-up")
  public ResponseEntity<?> loginUser(@RequestBody RegisterRequest request){
    return new ResponseEntity<>(this.userDetailsService.createUser(request), HttpStatus.OK);
  }
}
