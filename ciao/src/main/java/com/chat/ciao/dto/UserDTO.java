package com.chat.ciao.dto;

public class UserDTO {

  private long id;
  private String username;
  private String avatar;

  public UserDTO(){}

  public UserDTO(long id, String username, String avatar) {
    this.id = id;
    this.username = username;
    this.avatar = avatar;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
