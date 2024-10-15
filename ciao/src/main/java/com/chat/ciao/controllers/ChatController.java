package com.chat.ciao.controllers;

import com.chat.ciao.dto.ChatDTO;
import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iChatService;
import com.chat.ciao.services.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/chat")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ChatController {

  @Autowired
  private iChatService chatService;

  @Autowired
  private iUserService userService;

  @GetMapping("/get-all")
  public ResponseEntity<?> getAllChats(){
    Map<String, Object> response = new HashMap<>();
    try{
      List<Chat> chatList = this.chatService.findAll();
      response.put("chatList", chatList);
    } catch (Exception e){
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @GetMapping("/my-chats")
  public ResponseEntity<?> getMyChats(Principal principal){
    Map<String, Object> response = new HashMap<>();
    try{
      User myUser = this.userService.getByUsername(principal.getName());
      List<ChatDTO> chats = this.chatService.findAllByUserId(myUser.getId());
      response.put("chatList", chats);
    } catch (Exception e){
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getChatById(@PathVariable("id") long id){
    Map<String, Object> response = new HashMap<>();
    try{
      Chat chat = this.chatService.findById(id);
      response.put("chat", chat);
    } catch (Exception e){
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }


}
