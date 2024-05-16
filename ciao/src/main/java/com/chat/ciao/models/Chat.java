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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Message> messages;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<NewMessage> newMessages;

  public Chat(){
    this.participants = new ArrayList<User>();
    this.messages = new ArrayList<Message>();
    this.newMessages = new ArrayList<NewMessage>();
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

  public List<NewMessage> getNewMessages() {
    return newMessages;
  }

  public void setNewMessages(List<NewMessage> newMessages) {
    this.newMessages = newMessages;
  }


}
