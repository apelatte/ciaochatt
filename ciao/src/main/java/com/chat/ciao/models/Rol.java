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
  @Enumerated(EnumType.STRING)
  private RolEnum rolEnum;

  public Rol(){}

  public Rol(long id, RolEnum rolEnum) {
    this.id = id;
    this.rolEnum = rolEnum;
  }

  public Rol(RolEnum rolEnum) {
    this.rolEnum = rolEnum;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public RolEnum getRolEnum() {
    return rolEnum;
  }

  public void setRolEnum(RolEnum rolEnum) {
    this.rolEnum = rolEnum;
  }
}
