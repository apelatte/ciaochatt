package com.chat.ciao.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String username;
  private String password;
  private String avatar;
  private boolean isEnabled;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialNonExpired;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Rol rol;

  @OneToMany
  @JoinTable(
    name = "users_friends",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id")
  )

  private List<User> friends;
  @ManyToMany
  @JoinTable(
    name = "chats_participants",
    joinColumns = @JoinColumn(name = "participant_id"),
    inverseJoinColumns = @JoinColumn(name = "chat_id")
  )

  private List<Chat> chats;

  public User() {
  }

  public User(String username, String password, String avatar) {
    this.username = username;
    this.password = password;
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

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isCredentialNonExpired() {
    return credentialNonExpired;
  }

  public void setCredentialNonExpired(boolean credentialNonExpired) {
    this.credentialNonExpired = credentialNonExpired;
  }

  public Rol getRol() {
    return rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public List<User> getFriends() {
    return friends;
  }

  public void setFriends(List<User> friends) {
    this.friends = friends;
  }

  public List<Chat> getChats() {
    return chats;
  }

  public void setChats(List<Chat> chats) {
    this.chats = chats;
  }
}
