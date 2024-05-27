package com.chat.ciao.auth;

public class RegisterRequest {

  private String username;
  private String password;
  private String avatar;

  public RegisterRequest(){}

  public RegisterRequest(String username, String password, String avatar) {
    this.username = username;
    this.password = password;
    this.avatar = avatar;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
