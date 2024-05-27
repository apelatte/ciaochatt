package com.chat.ciao.services.impl;

import com.chat.ciao.auth.AuthResponse;
import com.chat.ciao.auth.LoginRequest;
import com.chat.ciao.auth.RegisterRequest;
import com.chat.ciao.dao.iUserDao;
import com.chat.ciao.models.Rol;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements iAuthService {

  @Autowired
  private iUserDao userDao;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public AuthResponse login(LoginRequest request){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    UserDetails user = userDao.findByUsername(request.getUsername()).orElseThrow();
    String token = jwtService.getToken(user);
    return new AuthResponse(token);
  }

  @Override
  public AuthResponse register(RegisterRequest request, Rol rol){
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(encoder.encode(request.getPassword()));
    user.setAvatar(request.getAvatar());
    user.setEnable(true);
    user.setRol(rol);
    userDao.save(user);

    return new AuthResponse(jwtService.getToken(user));
  }
}
