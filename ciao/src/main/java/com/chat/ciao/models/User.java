package com.chat.ciao.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private boolean enable;
  private String avatar;

  @ManyToOne
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
    this.friends = new ArrayList<User>();
  }

  public User(String username, String password, boolean enable, String avatar, Rol rol) {
    this.username = username;
    this.password = password;
    this.enable = enable;
    this.avatar = avatar;
    this.rol = rol;
    this.friends = new ArrayList<>();
    this.chats = new ArrayList<>();
  }

  public User(String username, String password, boolean enable, String avatar, Rol rol, List<User> friends, List<Chat> chats) {
    this.username = username;
    this.password = password;
    this.enable = enable;
    this.avatar = avatar;
    this.rol = rol;
    this.friends = friends;
    this.chats = chats;
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

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
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
