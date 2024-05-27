package com.chat.ciao.services;

import com.chat.ciao.models.Rol;

public interface iRolService {

  Rol findById(long id);
  Rol findByRolName(String rolName);
  Rol save(Rol rol);
  Rol delete(Rol rol);

}
