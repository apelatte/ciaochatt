package com.chat.ciao.controllers;

import com.chat.ciao.models.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  @MessageMapping("/chat/{roomId}")
  @SendTo("/topic/{roomId}")
  public Message chat(@DestinationVariable String roomId, Message message){
    return message;
  }
}
