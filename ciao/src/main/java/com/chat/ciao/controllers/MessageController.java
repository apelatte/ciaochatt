package com.chat.ciao.controllers;

import com.chat.ciao.dto.MessageDTO;
import com.chat.ciao.models.Chat;
import com.chat.ciao.models.Message;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iChatService;
import com.chat.ciao.services.iMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/messages/")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MessageController {

  @Autowired
  private iMessageService messageService;

  @Autowired
  private iChatService chatService;

  @GetMapping("/chat/{id}")
  public ResponseEntity<?> getMessagesByChatId(@PathVariable("id") long chatId){
    Map<String, Object> response = new HashMap<>();
    try {
      List<Message> messageList = this.messageService.findAllByChat(chatId);
      response.put("messageList", messageList);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @PostMapping("/chat")
  public ResponseEntity<?> sendMessage(@RequestBody Message message){
    Map<String, Object> response = new HashMap<>();
    try {
      message = this.messageService.save(message);
      response.put("message", message);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }
}
