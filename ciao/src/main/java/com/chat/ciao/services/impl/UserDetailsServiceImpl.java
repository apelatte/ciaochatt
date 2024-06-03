package com.chat.ciao.services.impl;

import com.chat.ciao.auth.AuthResponse;
import com.chat.ciao.auth.LoginRequest;
import com.chat.ciao.auth.RegisterRequest;
import com.chat.ciao.dao.iRolDao;
import com.chat.ciao.dao.iUserDao;
import com.chat.ciao.models.Rol;
import com.chat.ciao.models.RolEnum;
import com.chat.ciao.models.User;
import com.chat.ciao.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private iUserDao userDao;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private iRolDao rolDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userDao.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
    String userRol = user.getRol().getRolEnum().name();
    authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userRol)));

    return new org.springframework.security.core.userdetails.User(
      user.getUsername(),
      user.getPassword(),
      user.isEnabled(),
      user.isAccountNonExpired(),
      user.isCredentialNonExpired(),
      user.isAccountNonLocked(),
      authorityList);
  }

  public AuthResponse login(LoginRequest loginRequest){
    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();

    Authentication authentication = this.authenticate(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String accessToken = jwtUtils.createToken(authentication);

    return new AuthResponse(accessToken);
  }

  private Authentication authenticate(String username, String password) {
    UserDetails userDetails = this.loadUserByUsername(username);
    if(userDetails == null) throw new BadCredentialsException("Invalid username or password.");
    if(!passwordEncoder.matches(password, userDetails.getPassword())) throw new BadCredentialsException("Invalid password.");

    return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
  }

  public User createUser(RegisterRequest request){
    Rol rol = this.rolDao.findByRolEnum(RolEnum.USER).orElse(null);
    User user = new User();
    user.setUsername(request.getUsername().toLowerCase().trim());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setAvatar(request.getAvatar());
    user.setRol(rol);
    user.setEnabled(true);
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCredentialNonExpired(true);
    user.setFriends(new ArrayList<>());
    user.setChats(new ArrayList<>());
    User userCreated = userDao.save(user);
    return userCreated;
  }
}
