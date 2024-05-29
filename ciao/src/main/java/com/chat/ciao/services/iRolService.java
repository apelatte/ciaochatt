package com.chat.ciao.services;

import com.chat.ciao.models.Rol;

import java.util.List;

public interface iRolService {

  List<Rol> findAll();
  Rol findById(long id);
  Rol findByRolName(String rolName);
  Rol save(Rol rol);
  Rol delete(Rol rol);

}
