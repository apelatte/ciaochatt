package com.chat.ciao.services;

import com.chat.ciao.models.Rol;
import com.chat.ciao.models.RolEnum;

import java.util.List;

public interface iRolService {

  List<Rol> findAll();
  Rol findById(long id);
  Rol findByRolEnum(RolEnum rolEnum);
  Rol save(Rol rol);
  Rol delete(Rol rol);

}
