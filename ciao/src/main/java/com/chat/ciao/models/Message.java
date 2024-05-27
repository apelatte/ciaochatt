package com.chat.ciao.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table(name = "messages")
@Entity
public class Message implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String text;
  private Date time;
  @ManyToOne
  private User from;
  @ManyToOne
  private User to;

  public Message(){}

  public Message(String text, Date time, User from, User to) {
    this.text = text;
    this.time = time;
    this.from = from;
    this.to = to;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public User getFrom() {
    return from;
  }

  public void setFrom(User from) {
    this.from = from;
  }

  public User getTo() {
    return to;
  }

  public void setTo(User to) {
    this.to = to;
  }
}
