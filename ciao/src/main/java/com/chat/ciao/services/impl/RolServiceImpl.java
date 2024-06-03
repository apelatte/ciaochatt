package com.chat.ciao.services.impl;

import com.chat.ciao.dao.iRolDao;
import com.chat.ciao.models.Rol;
import com.chat.ciao.models.RolEnum;
import com.chat.ciao.services.iRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements iRolService {

  @Autowired
  private iRolDao rolDao;

  @Override
  public List<Rol> findAll() {
    return this.rolDao.findAll();
  }

  @Override
  public Rol findById(long id) {
    return this.rolDao.findById(id).orElse(null);
  }

  @Override
  public Rol findByRolEnum(RolEnum rolEnum) {
    return this.rolDao.findByRolEnum(rolEnum).orElse(null);
  }

  @Override
  public Rol save(Rol rol) {
    return this.rolDao.save(rol);
  }

  @Override
  public Rol delete(Rol rol) {
    this.rolDao.delete(rol);
    return rol;
  }
}
