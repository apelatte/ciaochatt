package com.chat.ciao.controllers;

import com.chat.ciao.models.User;
import com.chat.ciao.services.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("api/users")
public class UserController {

  @Autowired
  private iUserService userSvc;

  @GetMapping("/getAll")
  public ResponseEntity<?> getAllUsers(){
    Map<String, Object> response = new HashMap<>();
    try {
      List<User> userList = this.userSvc.getAll();
      response.put("userList", userList);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @GetMapping("/getById/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") long id){
    Map<String, Object> response = new HashMap<>();
    try {
      User user = this.userSvc.getById(id);
      response.put("user", user);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }
}
