package com.chat.ciao.controllers;

import com.chat.ciao.auth.AuthResponse;
import com.chat.ciao.auth.LoginRequest;
import com.chat.ciao.auth.RegisterRequest;
import com.chat.ciao.dto.UserDTO;
import com.chat.ciao.models.Rol;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iAuthService;
import com.chat.ciao.services.iRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private iAuthService authService;

  @Autowired
  private iRolService rolService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request){
    Map<String, Object> response = new HashMap<>();
    try {
      Rol rol = this.rolService.findByRolName("USER");
      if(rol == null) rol = this.rolService.save(new Rol("USER"));
      AuthResponse authResponse = authService.register(request, rol);
      if(authResponse.getToken() != null) response.put("token", authResponse.getToken());
    } catch (Exception e){
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
    Map<String, Object> response = new HashMap<>();
    try {
      AuthResponse auth = this.authService.login(request);
      response.put("token", auth);
    } catch (Exception e){
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }
}
