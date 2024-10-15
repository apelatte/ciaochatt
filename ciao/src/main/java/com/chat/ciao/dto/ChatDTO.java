package com.chat.ciao.dto;

import com.chat.ciao.models.Message;
import com.chat.ciao.models.NewMessage;
import com.chat.ciao.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDTO {

  private long id;
  private Date last_update;
  private UserDTO friend;
  private List<MessageDTO> messages;

  public ChatDTO() {
    this.messages = new ArrayList<>();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getLast_update() {
    return last_update;
  }

  public void setLast_update(Date last_update) {
    this.last_update = last_update;
  }

  public UserDTO getFriend() {
    return friend;
  }

  public void setFriend(UserDTO friend) {
    this.friend = friend;
  }

  public List<MessageDTO> getMessages() {
    return messages;
  }

  public void setMessages(List<MessageDTO> messages) {
    this.messages = messages;
  }
}
