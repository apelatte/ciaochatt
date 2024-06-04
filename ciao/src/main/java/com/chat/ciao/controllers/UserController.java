package com.chat.ciao.controllers;

import com.chat.ciao.dto.UserDTO;
import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("api/users")
public class UserController {

  @Autowired
  private iUserService userSvc;

  @GetMapping("/getAll")
  public ResponseEntity<?> getAllUsers() {
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
  public ResponseEntity<?> getById(@PathVariable("id") long id) {
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

  @GetMapping("/my-user")
  public ResponseEntity<?> getMyUser(Principal principal) {
    Map<String, Object> response = new HashMap<>();
    try {
      User user = this.userSvc.getByUsername(principal.getName());
      response.put("myUser", user);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @GetMapping("/friends")
  public ResponseEntity<?> getFriends(Principal principal) {
    Map<String, Object> response = new HashMap<>();
    try {
      User user = this.userSvc.getByUsername(principal.getName());
      List<UserDTO> friends = user.getFriends().stream()
        .map(friend -> new UserDTO(friend.getId(), friend.getUsername(), friend.getAvatar())).toList();
      response.put("friends", friends);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @GetMapping("/chats")
  public ResponseEntity<?> getChats(Principal principal) {
    Map<String, Object> response = new HashMap<>();
    try {
      User user = this.userSvc.getByUsername(principal.getName());
      List<Chat> chatList = user.getChats();
      response.put("chatList", chatList);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @PostMapping("/add-friend")
  public ResponseEntity<?> addFriend(@RequestBody String friendUsername, Principal principal) {
    Map<String, Object> response = new HashMap<>();
    try {
      User myUser = this.userSvc.getByUsername(principal.getName());
      User newFriend = this.userSvc.getByUsername(friendUsername);
      myUser.getFriends().add(newFriend);
      myUser = this.userSvc.save(myUser);
      response.put("friendList", myUser.getFriends());
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }
}
