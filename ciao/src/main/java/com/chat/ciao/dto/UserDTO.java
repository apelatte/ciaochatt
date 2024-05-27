package com.chat.ciao.dto;

import com.chat.ciao.models.Chat;
import com.chat.ciao.models.Rol;
import com.chat.ciao.models.User;
import jakarta.persistence.*;

import java.util.List;

public class UserDTO {

  private String username;
  private String password;
  private String avatar;

  public UserDTO(){}

  public UserDTO(String username, String password, String avatar) {
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
