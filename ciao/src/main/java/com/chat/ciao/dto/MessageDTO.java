package com.chat.ciao.dto;

import java.util.Date;

public class MessageDTO {

  private long id;
  private String text;
  private Date time;
  private long fromID;
  private long toID;
  private long chatID;

  public MessageDTO(){}

  public MessageDTO(long id, String text, Date time, long fromID, long toID, long chatID) {
    this.id = id;
    this.text = text;
    this.time = time;
    this.fromID = fromID;
    this.toID = toID;
    this.chatID = chatID;
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

  public long getFromID() {
    return fromID;
  }

  public void setFromID(long fromID) {
    this.fromID = fromID;
  }

  public long getToID() {
    return toID;
  }

  public void setToID(long toID) {
    this.toID = toID;
  }

  public long getChatID() {
    return chatID;
  }

  public void setChatID(long chatID) {
    this.chatID = chatID;
  }
}
