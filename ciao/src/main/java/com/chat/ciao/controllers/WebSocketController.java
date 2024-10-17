package com.chat.ciao.controllers;

import com.chat.ciao.dto.ChatDTO;
import com.chat.ciao.dto.MessageDTO;
import com.chat.ciao.models.Chat;
import com.chat.ciao.models.Message;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iChatService;
import com.chat.ciao.services.iMessageService;
import com.chat.ciao.services.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebSocketController {

  @Autowired
  private iUserService userService;

  @Autowired
  private iMessageService messageService;

  @Autowired
  private iChatService chatService;

  @MessageMapping("/chat/{roomId}")
  @SendTo("/topic/{roomId}")
  public ResponseEntity<?> chat(@DestinationVariable String roomId, MessageDTO message){
    Map<String, Object> response = new HashMap<>();
    try{
      User myUser = this.userService.getById(message.getFromID());
      User friend = this.userService.getById(message.getToID());
      Chat currentChat = this.chatService.findById(message.getChatID());
      Message newMessage = new Message();
      newMessage.setText(message.getText());
      newMessage.setFrom(myUser);
      newMessage.setTo(friend);
      newMessage.setTime(message.getTime());

      currentChat.getMessages().add(newMessage);
      currentChat = this.chatService.save(currentChat);
      response.put("chat", currentChat);

    } catch (Exception e){
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
  }
}
