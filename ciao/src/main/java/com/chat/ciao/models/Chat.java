package com.chat.ciao.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "chats")
@Entity
public class Chat implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Date last_update;

  @ManyToMany(mappedBy = "chats")
  private List<User> participants;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Message> messages;

  public Chat(){
    this.participants = new ArrayList<User>();
    this.messages = new ArrayList<Message>();
  }

  public Chat(List<User> participants) {
    this.participants = participants;
    this.messages = new ArrayList<Message>();
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

  public List<User> getParticipants() {
    return participants;
  }

  public void setParticipants(List<User> participants) {
    this.participants = participants;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

}
