package com.chat.ciao.dto;

import java.util.Date;

public class MessageDTO {

  private String text;
  private Date time;
  private long chatID;
  private long fromID;
  private long toID;

  public MessageDTO(){}

  public MessageDTO(String text, Date time, long chatID, long fromID, long toID) {
    this.text = text;
    this.time = time;
    this.chatID = chatID;
    this.fromID = fromID;
    this.toID = toID;
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

  public long getChatID() {
    return chatID;
  }

  public void setChatID(long chatID) {
    this.chatID = chatID;
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
}
