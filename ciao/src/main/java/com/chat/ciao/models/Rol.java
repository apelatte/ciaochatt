package com.chat.ciao.models;

import com.chat.ciao.Role;
import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "roles")
@Entity
public class Rol implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(unique = true)
  private String rolName;

  public Rol(){}

  public Rol(String rolName) {
    this.rolName = rolName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRolName() {
    return rolName;
  }

  public void setRolName(String rolName) {
    this.rolName = rolName;
  }
}
