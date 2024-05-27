package com.chat.ciao.services;

import com.chat.ciao.auth.AuthResponse;
import com.chat.ciao.auth.LoginRequest;
import com.chat.ciao.auth.RegisterRequest;
import com.chat.ciao.models.Rol;

public interface iAuthService {

  AuthResponse login(LoginRequest request);
  AuthResponse register(RegisterRequest request, Rol rol);
}
