package com.chat.ciao.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDTO {

  private long id;
  private Date last_update;
  private List<UserDTO> participants;
  private List<MessageDTO> messages;

  public ChatDTO() {
    this.messages = new ArrayList<>();
    this.participants = new ArrayList<>();
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

  public List<UserDTO> getParticipants() {
    return participants;
  }

  public void setParticipants(List<UserDTO> participants) {
    this.participants = participants;
  }

  public List<MessageDTO> getMessages() {
    return messages;
  }

  public void setMessages(List<MessageDTO> messages) {
    this.messages = messages;
  }
}
