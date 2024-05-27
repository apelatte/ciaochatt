package com.chat.ciao.auth;

public class AuthResponse {

  private String token;

  public AuthResponse(){
    this.token = null;
  }

  public AuthResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
