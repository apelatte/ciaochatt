package com.chat.ciao.models;

import jakarta.persistence.*;
import org.apache.logging.log4j.message.Message;

import java.io.Serializable;

@Table(name = "new_messages")
@Entity
public class NewMessage implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Chat chat;
  @ManyToOne
  private User from;
  private Message message;

  public NewMessage(){}

  public NewMessage(Chat chat, User from, Message message) {
    this.chat = chat;
    this.from = from;
    this.message = message;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Chat getChat() {
    return chat;
  }

  public void setChat(Chat chat) {
    this.chat = chat;
  }

  public User getFrom() {
    return from;
  }

  public void setFrom(User from) {
    this.from = from;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }
}
